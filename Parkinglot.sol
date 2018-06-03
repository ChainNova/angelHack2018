pragma solidity ^0.4.22;

/// @title Voting with delegation.
contract Ballot {
    // This declares a new complex type which will
    // be used for variables later.
    // It will represent a single voter.
    struct ParkingLot {
        string id;
        address owner;
        uint status; // 0 is free, 1 mean rented, 2 mean using
        uint flag;
    }
    
    struct ParkingContract {
        address renter;
        string parkingId;
        uint16 price;
        uint16 startTime;
        uint16 endTime;
        uint flag;
    }
    

    
    mapping(string => ParkingContract)  parkingContracts;
    mapping(string => ParkingLot)  parkingLots;
    mapping(address => ParkingContract)  renterContracts;
    address constant empty_addr = address(1);
    
     function createParkingLot(string id) public { 
         ParkingLot memory _parking = ParkingLot({
             id: id,
             owner: msg.sender,
             status : uint16(0),
             flag: 1
         });
        parkingLots[id] = _parking;
     }
     
     function publish(string pid, uint16 price, uint16 startTime, uint16 endTime) public {
         require(parkingLots[pid].flag==1);
         
         ParkingLot storage _parking = parkingLots[pid];
         require(_parking.status ==0);
         if (msg.sender!=_parking.owner) return;
         
         ParkingContract memory _contract = ParkingContract({
             
             parkingId: pid,
             price:price,
             startTime: startTime,
             endTime:endTime,
             flag:1,
             renter:empty_addr
         });
         parkingContracts[pid] = _contract;
         
            
     }
     
     function rent(string id) public {
         
         require(parkingContracts[id].flag==1);
    
    
         ParkingContract storage _contract = parkingContracts[id];
         
         ParkingLot storage  _parking = parkingLots[_contract.parkingId];
         
        require(_contract.renter == empty_addr);
        require(_parking.status ==0);
       
        
         
         _parking.status = 1;
         _contract.renter = msg.sender;
         renterContracts[msg.sender] = _contract;
     }
     
    function proveRenter(string id, address renter) public view returns(bool res){
        if (parkingContracts[id].flag!=1 ){
            return false;
        }
        if(parkingContracts[id].renter!=renter ){
            return false;
        }
        return true;
    }
    
    function use(string id) public{
        ParkingContract storage _contract = renterContracts[msg.sender];
        ParkingLot storage _parking = parkingLots[id];
        require( _parking.status == 1 );
        if (_contract.renter == msg.sender){
            _parking.status = 2; 
        }
        
        
    }
    
     function release(string id) public{
        require(parkingContracts[id].renter == msg.sender);
        ParkingLot storage _parking = parkingLots[id];
        _parking.status=0;
        delete parkingContracts[id];
        delete renterContracts[msg.sender];
    }
}