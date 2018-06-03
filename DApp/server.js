var Web3 = require("web3");

const abi = [{"constant":false,"inputs":[{"name":"pid","type":"string"},{"name":"price","type":"uint16"},{"name":"startTime","type":"uint16"},{"name":"endTime","type":"uint16"}],"name":"publish","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"id","type":"string"}],"name":"rent","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"id","type":"string"}],"name":"use","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"name":"id","type":"string"},{"name":"renter","type":"address"}],"name":"proveRenter","outputs":[{"name":"res","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"id","type":"string"}],"name":"createParkingLot","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"id","type":"string"}],"name":"release","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"}];

var web3 = new Web3("http://localhost:7299");
var contract = new web3.eth.Contract(abi,"0x9b02a1827a3ac3b5ccfe538cd940b32533b7b7b1")
var base_account = '0xe8a02d3a95d027a1e76aefa6933921b216cfd91f'
var base_account1 = '0x45af1f62cd6dd1ac7432f970a4953019b3174163'

var methods = contract.methods;

var express = require('express');
var app = express();

app.get('/publish/:pid', function (req, res) {
	methods.publish(req.params.pid,parseInt(req.query.price,10),parseInt(req.query.startTime,10),parseInt(req.query.endTime,10) ).send({'from':base_account1,gas: 30000000},function(error, result){ 
		console.log(error)
		console.log(typeof(error))
		console.log(result)
		if (!error && typeof(error)!="undefined" && error!=0){
   			res.send('success');
			return
		}
		console.log(error)
		console.log(typeof(error))
   		res.send('failed');
	
	} )
})


app.get('/rent/:pid', function (req, res) {
	methods.rent(req.params.pid).send({'from':base_account,gas: 30000000},function(error, result){ 
		console.log(error)
		console.log(typeof(error))
		if (!error && typeof(error)!="undefined" && error!=0){
   			res.send('success');
			return
		}
   		res.send('failed');
	
	} )
})


app.get('/use/:pid', function (req, res) {
	methods.use(req.params.pid).send({'from':base_account},function(error, result){ 

		console.log(error)
		console.log(typeof(error))
		if (!error && typeof(error)!="undefined" && error!=0){
   			res.send('success');
			return
		}
   		res.send('failed');
	
	} )
})

app.get('/release/:pid', function (req, res) {
	methods.release(req.params.pid).send({'from':base_account},function(error, result){ 

		console.log(error)
		console.log(typeof(error))
		if (!error && typeof(error)!="undefined" && error!=0){

   			res.send('success');
			return
		}
   		res.send('failed');
	
	} )
})


app.get('/prove/:pid', function (req, res) {
	methods.proveRenter(req.params.pid,'0x84710eaa3fe3f9501531f424bb2597d8cdc0ea84' ).call({'from':base_account},function(error, result){ 
		if (!error && typeof(error)!="undefined" && error!=0){
			console.log(result)
   			//res.send(res);
   			res.send(result);
			return
		}
   		res.send('failed');
	
	} )
})

var server = app.listen(7300, function () {

   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})
