pragma solidity ^0.4.0;
contract ShardedParking {

    // Unoccupied Section for Parking Lot
    struct UnoccupiedSection {
        address owner;          // owner of the parking lot
        uint16 name;            // unique identifier of parking lot
        uint16 longitude;       // longitude of parking lot
        uint16 latitude;        // latitude of parking lot
        uint16 startTime;       // startTime of parking lot
        uint16 endTime;         // startTime of parking lot
        uint16 price;           // price of parking lot per time unit
    }
    
    // Unoccupied Section for Parking Lot
    struct OccupiedSection {
        address owner;          // owner of the parking lot
        uint16 name;            // unique identifier of parking lot
        uint16 longitude;       // longitude of parking lot
        uint16 latitude;        // latitude of parking lot
        uint16 startTime;       // startTime of parking lot
        uint16 endTime;         // endTime of parking lot
        uint16 price;           // price of parking lot per time unit
        uint status;            // status of parking lot, 0-idle, 1-locked, 2-rent, 3-finished
        address renter;         // renter of parking lot
    }
    
    struct Renter{
        uint16 balance;                           // balance of renter
        OccupiedSection[] occupiedSections;       // Occupied Sections of renter
        UnoccupiedSection[] unoccupiedSections;   // Unoccupied Sections of renter, if occupied, will display in renter's occupied sections other than here
    }
    
    mapping(address => Renter) renters;           // all renters
    UnoccupiedSection[] gUnoccupiedSections;      // all Unoccupied Sections
    
    /// Create a new ShardedParking.
    function ShardedParking() public {
        
    }
    
    // recharge balance
    // TODO: for temp using
    function recharge(uint16 charge) public {
        Renter r = renters[msg.sender];
        r.balance += charge;
        
        renters[msg.sender] = r;
    }
    
    // get account information
    function getAccount() constant public returns (uint16 balance, uint16[] namesOccupied, uint16[] namesUnoccupied) {
        Renter r = renters[msg.sender];
        balance = r.balance;
        
        namesOccupied = new uint16[](r.occupiedSections.length);
        namesUnoccupied = new uint16[](r.unoccupiedSections.length);
        
        for (uint8 i=0; i<r.occupiedSections.length; i++) {
            namesOccupied[i] = r.occupiedSections[i].name;
        }
        
        for (i=0; i<r.unoccupiedSections.length; i++) {
            namesUnoccupied[i]= r.unoccupiedSections[i].name;
        }
        
        return;
    }
    
    // get account balance
    function getBalance() constant public returns (uint16 balance) {
        Renter r = renters[msg.sender];
        return r.balance;
    }
    
    // publish parking lot
    function publishParkingLot(uint16 name, uint16 longitude, uint16 latitude, uint16 startTime, uint16 endTime, uint16 price) public {
        UnoccupiedSection memory unoccupiedSection = UnoccupiedSection({
            owner: msg.sender,
            name: name,
            longitude: longitude,
            latitude: latitude,
            startTime: startTime,
            endTime: endTime,
            price: price
        });
      
        gUnoccupiedSections.push(unoccupiedSection);
        
        Renter storage r = renters[msg.sender];
        r.unoccupiedSections.push(unoccupiedSection);
        
        renters[msg.sender] = r;
    }
    
    // get all parking lots
    function getParkingLots() public constant returns  (uint16[] names, uint16[] longitudes, uint16[] latitudes, uint16[] startTimes, uint16[] endTimes, uint16[] prices) {
        names = new uint16[](gUnoccupiedSections.length);
        longitudes = new uint16[](gUnoccupiedSections.length);
        latitudes = new uint16[](gUnoccupiedSections.length);
        startTimes = new uint16[](gUnoccupiedSections.length);
        endTimes = new uint16[](gUnoccupiedSections.length);
        prices = new uint16[](gUnoccupiedSections.length);
        for (uint8 i=0; i<gUnoccupiedSections.length; i++) {
            names[i] = gUnoccupiedSections[i].name;
            longitudes[i] = gUnoccupiedSections[i].longitude;
            latitudes[i] = gUnoccupiedSections[i].latitude;
            startTimes[i] = gUnoccupiedSections[i].startTime;
            endTimes[i] = gUnoccupiedSections[i].endTime;
            prices[i] = gUnoccupiedSections[i].price;
        }
    }
    
    // get parking lot according to distance
    // TODO: compute square according to actual longitude & latitude
    function getParkingLot(uint16 longitude, uint16 latitude, uint16 distance) public constant returns  (uint16[] names, uint16[] longitudes, uint16[] latitudes, uint16[] startTimes, uint16[] endTimes, uint16[] prices) {
        names = new uint16[](gUnoccupiedSections.length);
        longitudes = new uint16[](gUnoccupiedSections.length);
        latitudes = new uint16[](gUnoccupiedSections.length);
        startTimes = new uint16[](gUnoccupiedSections.length);
        endTimes = new uint16[](gUnoccupiedSections.length);
        prices = new uint16[](gUnoccupiedSections.length);
        uint8 index = 0;
        for (uint8 i=0; i<gUnoccupiedSections.length; i++) {
            uint16 square = (gUnoccupiedSections[i].longitude-longitude)*(gUnoccupiedSections[i].longitude-longitude) + (gUnoccupiedSections[i].latitude-latitude)*(gUnoccupiedSections[i].latitude-latitude);
            if (square <= distance*distance) {
                names[index] = gUnoccupiedSections[i].name;
                longitudes[index] = gUnoccupiedSections[i].longitude;
                latitudes[index] = gUnoccupiedSections[i].latitude;
                startTimes[index] = gUnoccupiedSections[i].startTime;
                endTimes[index] = gUnoccupiedSections[i].endTime;
                prices[index] = gUnoccupiedSections[i].price;
                
                index++;
            }
        }
    }
    
    // locking parking lot
    function lockingParkingLot(uint16 name, uint16 startTime, uint16 endTime) public returns (bool locked) {
        address renter = msg.sender;
        Renter storage r = renters[renter];
        for (uint8 i=0; i<gUnoccupiedSections.length; i++) {
            if (gUnoccupiedSections[i].name == name && gUnoccupiedSections[i].startTime <= startTime && gUnoccupiedSections[i].endTime >= endTime) {
                Renter storage o = renters[gUnoccupiedSections[i].owner];
                for (uint8 j=0; j<o.unoccupiedSections.length; j++) {
                    if (o.unoccupiedSections[j].name == name && o.unoccupiedSections[j].startTime <= startTime && o.unoccupiedSections[j].endTime >= endTime) {
                        // compute the balance
                        uint16 charge = (endTime - startTime) * o.unoccupiedSections[j].price;
                        if (r.balance >= charge) {
                            r.balance -= charge;
                            o.balance += charge;
                            
                            OccupiedSection memory occupiedSection = OccupiedSection({
                                owner: gUnoccupiedSections[i].owner,
                                name: gUnoccupiedSections[i].name,
                                longitude: gUnoccupiedSections[i].longitude,
                                latitude: gUnoccupiedSections[i].latitude,
                                startTime: startTime,
                                endTime: endTime,
                                price: gUnoccupiedSections[i].price,
                                status: uint(1),
                                renter: renter
                            });
                            r.occupiedSections.push(occupiedSection);
                            renters[renter] = r;
                            renters[gUnoccupiedSections[i].owner] = o;
                            
                            delete o.unoccupiedSections[j];
                            
                            // TODO: split into 2 part
                            delete gUnoccupiedSections[i];
                
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    // start rent parking lot
    function startParkingLot(uint16 name, uint16 startTime, address renter) public returns (bool rent) {
        Renter r = renters[renter];
        for (uint8 i=0; i<r.occupiedSections.length; i++) {
            if (r.occupiedSections[i].name == name && r.occupiedSections[i].startTime <= startTime && r.occupiedSections[i].endTime >= startTime && uint(1) == r.occupiedSections[i].status) {
                r.occupiedSections[i].status = uint(2);
                
                renters[renter] = r;
                return true;
            }
        }
        
        return false;
    }
    
    // finish parking lot
    function stopParkingLot(uint16 name, uint16 endTime, address renter) public returns (bool stoped) {
        Renter r = renters[renter];
        for (uint8 i=0; i<r.occupiedSections.length; i++) {
            if (r.occupiedSections[i].name == name && r.occupiedSections[i].startTime <= endTime && r.occupiedSections[i].endTime >= endTime && uint(2) == r.occupiedSections[i].status) {
                Renter o = renters[r.occupiedSections[i].owner];
                // o.balance += (r.occupiedSections[i].endTime - r.occupiedSections[i].startTime) * r.occupiedSections[i].price;
                
                // unify two unoccupiedSections
                bool unified = false;
                for (uint8 j=0; j<o.unoccupiedSections.length; j++) {
                    if (o.unoccupiedSections[j].endTime == r.occupiedSections[i].startTime) {
                        o.unoccupiedSections[j].endTime = r.occupiedSections[i].endTime;
                        
                        unified = true;
                    }
                }
                
                if (unified != false) {
                    UnoccupiedSection memory unoccupiedSection = UnoccupiedSection({
                        owner: r.occupiedSections[i].owner,
                        name: r.occupiedSections[i].name,
                        longitude: r.occupiedSections[i].longitude,
                        latitude: r.occupiedSections[i].latitude,
                        startTime: r.occupiedSections[i].startTime,
                        endTime: r.occupiedSections[i].endTime,
                        price: r.occupiedSections[i].price
                    });
                
                    o.unoccupiedSections.push(unoccupiedSection);
                }
                renters[r.occupiedSections[i].owner] = o;
                
                delete r.occupiedSections[i];
                renters[renter] = r;
                
                return true;
            }
        }
        
        return false;
    }
}