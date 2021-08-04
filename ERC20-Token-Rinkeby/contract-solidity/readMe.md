# ERC20 FaucetToken Contract

## Rinkeby Contract Address
https://rinkeby.etherscan.io/address/0x650d2bca17aaf6b5979b53b221d409984ae887e8#code

This contract is faucet token contract.

## To run locally
### Prerequisites
Truffle environment is required to run the task locally

Install dependencies
`$ yarn install`

## Compile contract
`$ truffle compile`

## Deploy on rinkeby
before executing command, you need to put your wallet mnemonic key on truffle-config.js
`$ truffle deploy --network rinkeby`

## Verify contract
`$ truffle run verify FaucetToken --network rinkeby --license MIT`

## Test contract locally
`$ truffle test` (to run this, you need to install docker)

## Implementation

// FaucetToken contract is extended from ERC20 standard with burn features and ownable contract.

contract FaucetToken is ERC20Burnable, Ownable {

    using SafeMath for uint256;

    // stores global limit per address
    uint256 public constant globalLimit = 10000000;   // global limit per address

    // stores daily limit per day
    uint256 public constant dailyLimit = 100000;      // daily limit per day

    // stores next day's timestamp
    uint256 public constant dailyTime = 86400;  // 24 hours = 86400 seconds

    // stores next day timestamp
    uint256 public nextDayTimestamp;

    // stores requested amount current day
    uint256 public dailyAmount = 0;

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
