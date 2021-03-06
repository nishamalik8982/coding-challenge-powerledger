// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "@openzeppelin/contracts/utils/math/SafeMath.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/token/ERC20/extensions/ERC20Burnable.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract FaucetToken is ERC20Burnable, Ownable {

    using SafeMath for uint256;

    uint256 public constant globalLimit = 10000000;         // global limit per address
    uint256 public constant dailyLimit = 100000;            // daily limit per day
    uint256 public constant dailyTime = 86400;              // 24 hours = 86400 seconds
    uint256 public nextDayTimestamp;                        // next day timestamp
    uint256 public dailyAmount = 0;                         // requested amount current day

    // stores global amount of all addresses
    mapping(address => uint256) public globalAmount;

    // constructor: token Name - Faucet Token, Token Symbol - Faucet
    constructor() ERC20("Faucet Token", "Faucet") {
        nextDayTimestamp = block.timestamp.add(dailyTime);
    }
    // mint amount of token to recipient, only callable by contract deployer(owner)
    function mint(address recipient, uint256 amount) public onlyOwner returns (bool) {
        uint256 balanceBefore = balanceOf(recipient);
        _mint(recipient, amount);
        uint256 balanceAfter = balanceOf(recipient);
        return balanceAfter > balanceBefore;
    }

    // send amount of faucet token to recipient, anybody can call this function
    // request amount should not exceed 1000, global amount of users should not exceed globalLimit, requested daily amount should not exceed daily limit
    function getFaucet(address recipient, uint256 amount) external {
        require(amount >= 0, "FaucetToken: cannot get more than 100 tokens at once");
        require(amount <= 100000, "FaucetToken: cannot get more than 100 tokens at once");
        require(globalAmount[msg.sender].add(amount) <= globalLimit, "FaucetToken: exceed global limit per address");

        if(block.timestamp > nextDayTimestamp){
            dailyAmount = 0;
            nextDayTimestamp = nextDayTimestamp.add(dailyTime);
        }

        require(dailyAmount.add(amount) <= dailyLimit, "FaucetToken: exceed daily limit");
        mint(msg.sender, amount);
        dailyAmount = dailyAmount.add(amount);
        globalAmount[msg.sender] = globalAmount[msg.sender].add(amount);
    }

    // this is ERC20 standard method, check state before token transfer
    function _beforeTokenTransfer(address from, address to, uint256 amount) internal virtual override(ERC20) {
        super._beforeTokenTransfer(from, to, amount);
    }

    // returns remaining Faucet token amount current day
    function getDailyRemainingAmount() external view returns(uint256) {
        if(block.timestamp > nextDayTimestamp) return dailyLimit;
        return dailyLimit.sub(dailyAmount);
    }
}
