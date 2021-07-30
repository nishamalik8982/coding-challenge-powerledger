const FaucetToken = artifacts.require("FaucetToken");

const router = '0xd99d1c33f9fc3444f8101754abc46c52416550d1';
const dao = '0xa3A6064A20700e4ed956569190345094Bf4af370';

module.exports = function(deployer) {

  deployer.deploy(FaucetToken).then(() => {
    console.log('FaucetToken address: ', FaucetToken.address);
  });

};
