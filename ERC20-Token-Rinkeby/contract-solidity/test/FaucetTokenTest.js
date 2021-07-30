const FaucetTokenContract = artifacts.require('../FaucetToken.sol');
const truffleAssert = require('truffle-assertions');
let instance;

const user1 = '0x9B0a7E89a2348112ea9edDE216412Cae55F5EfBF';
const user2 = '0x1296Fef68289F75Ff7c75f71b34D4580aDc6864e';
const user3 = '0xaAeba8695f1bde1e0275a05ed14e744f53194eCF';

contract("CodeScreenContract", accounts => {

    beforeEach('setup', async () => {
        instance = await FaucetTokenContract.new({from: accounts[0]});
    });

    it("getFaucet: user1 send request to owner, owner send 1000 faucet to user1", async function() {
        await instance.getFaucet(user1, 100000000, {from: accounts[0]});
        const balance = await instance.balanceOf(user1);
        assert.equal(balance, 100000000);
    });

    it("getFaucet: users only request at most 1000 faucet, otherwise get error", async function() {
        await truffleAssert.reverts(
            instance.getFaucet(user1, 1000000000, { from: accounts[0] }),
            "FaucetToken: cannot get more than 100 tokens at once"
        );
    });

    it("getFaucet: users only request to owner, otherwise get error", async function() {
        await truffleAssert.reverts(
            instance.getFaucet(user1, 1000000000, { from: user2 }),
            "Ownable: caller is not the owner"
        );
    });

    it("getFaucet: user2 send request to owner, owner send 1000 faucet to user2", async function() {
        await instance.getFaucet(user2, 100000000, {from: accounts[0]})
        const balance = await instance.balanceOf(user2);
        assert.equal(balance, 100000000);
    });

    it("getFaucet: user2 send request to owner, owner send 1000 faucet to user2", async function() {
        await instance.getFaucet(user2, 100000000, {from: accounts[0]})
        const balance = await instance.balanceOf(user2);
        assert.equal(balance, 200000000);
    });

    it("getFaucet: user3 send request to owner, owner send 1000 faucet to user3", async function() {
        await instance.getFaucet(user3, 100000000, {from: accounts[0]})
        const balance = await instance.balanceOf(user3);
        assert.equal(balance, 100000000);
    });

    it("getRemainingDailyLimit: get remaining daily limit", async function() {
        const balance = await instance.getDailyRemainingAmount();
        assert.equal(balance, 9600000000);
    });
});
