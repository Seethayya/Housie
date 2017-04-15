app.controller('housieController', function($scope, $http, $interval) {
	$scope.customerId='';
	$scope.roomId='';
	$scope.ticketId='';
	$scope.loginDetails=false;
	$scope.registrationDetails=false;
	$scope.generateTicketDetails=false;
	$scope.joinRoomDetails=false;
	$scope.loginTab=true;
	$scope.registerTab=true;
	$scope.generateTicketTab=false;
	$scope.joinRoomTab=false;
	$scope.roomSearch=false;
	$scope.roomTypes = ["ALL_ONCE_PONTS", "NO_LINES_POINTS", "ONLY_FULL_POINTS", "ONLY_FAST_FIVE_POINTS"];
	$scope.ticketData=[];
	$scope.bgclr='';
	$scope.loginResponse='';
	$scope.startGameBut=true;
	$scope.customerName='';
	$scope.registerResponse='';
	$scope.roomSearchStatus='';
	$scope.fast5 = ''; $scope.line1 = '';$scope.line2 = ''; $scope.line3 = '';$scope.full = '';
	// store the interval promise in this variable
    var promise;
	$scope.getCutomerDetails = function() {
		$http.get("/api/getCustomerByName/"+$scope.firstName)
	    .then(function(response) {
	        $scope.customers = response.data;
	    });
	}
	//Tab clicks
    $scope.loginTabClick = function() {
    	$scope.loginDetails=true;
    	//reset others
    	$scope.registrationDetails=false;
    	$scope.generateTicketDetails=false;
    	$scope.joinRoomDetails=false;
    }
    $scope.registerTabClick = function() {
    	$scope.registrationDetails=true;
    	//reset others
    	$scope.loginDetails=false;
    	$scope.generateTicketDetails=false;
    	$scope.joinRoomDetails=false;
    }
    $scope.generateTicketTabClick = function() {
    	$scope.generateTicketDetails=true;
    	//reset others
    	$scope.registrationDetails=false;
    	$scope.loginDetails=false;
    	$scope.joinRoomDetails=false;
    }
    $scope.joinRoomTabClick = function() {
    	$scope.joinRoomDetails=true;
    	$scope.roomSearch=true;
    	//reset others
    	$scope.registrationDetails=false;
    	$scope.generateTicketDetails=false;
    	$scope.loginDetails=false;
    }
    
	$scope.login = function() {
		var dataObj = {
				"firstName" : $scope.userName,
				"password" : $scope.password
		};
		$http.post('/api/login', dataObj)
		.then(function(response) {
			$scope.loginResponse = response.data.status;
			if ($scope.loginResponse != 'Error:UserName/Password in correct or not exist') {
				$scope.customerId=response.data.customerId;
				$scope.customerName=response.data.customerName;
				$scope.generateTicketTab=true;
				$scope.joinRoomTab=true;
				$scope.loginTab=false;
				$scope.registerTab=false;
				$scope.loginDetails=false;
			} else {
				
			}
		}).then(function(response) {
			//$scope.loginResponse = response;
			
		});
	}
	
	$scope.register = function() {
		var dataObj = {
				"firstName" : $scope.customerFirstName,
				"lastName" : $scope.customerLastName,
				"middleName" : $scope.customerMiddleName,
				"password" : $scope.customePassword,
				"emailId" : $scope.customerEmailId,
				"mobileNo" : $scope.customerMobileNo
		};
		$http.post('/api/saveCustomer', dataObj)
		.then(function(response) {
			$scope.registerResponse = response.data.status;
			if ($scope.registerResponse != 'Error:Email Id already exist') {
				$scope.customerId=response.data.customerId;
				$scope.customerName=response.data.customerName;
				$scope.generateTicketTab=true;
				$scope.joinRoomTab=true;
				$scope.loginTab=false;
				$scope.registerTab=false;
				$scope.loginDetails=false;
				$scope.registrationDetails=false;
			} else {
				
			}
		}).then(function(response) {
			//$scope.loginResponse = response;
			
		});
	}
	
	$scope.generateTicket = function() {
		$http.get("/api/generateTicket/"+$scope.customerId+"/"+$scope.noOfTicketsToGenerate)
	    .then(function(response) {
	        $scope.responseGenerateTickets = response.data.status;
	    });
	}
	
	$scope.joinRoom = function() {
		$http.get("/api/bookRoom/"+$scope.customerId+"/"+$scope.roomType)
	    .then(function(response) {
	    	$scope.roomSearchStatus=response.data.status;
	    	if (response.data.roomId != null && response.data.roomId != '') {
		        $scope.roomId = response.data.roomId;
		        $scope.ticketId = response.data.ticketId;
		        $scope.roomDetails=true;
		        $scope.completedNos ='';
		        $scope.ticketData=response.data.ticket;
		        /*angular.forEach(response.data.ticket,function(value,index){
	                alert(value.name);
	            })*/
		        $scope.roomSearch=false;
	    	}
	    });
	}
	
	var nextNoButFun = function nextNoBut(){
		$http.get("/api/nextToken/"+$scope.roomId)
	    .then(function(response) {
	        $scope.nextNo = response.data.nextNo;
	        if ($scope.nextNo == 'All nos Passed') {
	        	$scope.resultRes = 'Game Completed';
	        	$scope.stop();
	        }
	    });
		if ($scope.nextNo != 'All nos Passed') {
			$http.get("/api/getCompletedNos/"+$scope.roomId)
		    .then(function(response) {
		        $scope.completedNos = response.data.completedNos;
		    });
		}
		$http.get("/api/getAllResults/"+$scope.roomId)
	    .then(function(response) {
	    	if (response.data != null && "SUCCESS" == response.data.status && response.data.results != '') {
	    		if (response.data.results['FAST5'] != null && response.data.results['FAST5'] != '') {
	    			$scope.fast5 = response.data.results['FAST5'];
	    		}
	    		if (response.data.results['LINE1'] != null && response.data.results['LINE1'] != '') {
	    			$scope.line1 = response.data.results['LINE1'];
	    		}
	    		if (response.data.results['LINE2'] != null && response.data.results['LINE2'] != '') {
	    			$scope.line2 = response.data.results['LINE2'];
	    		}
	    		if (response.data.results['LINE3'] != null && response.data.results['LINE3'] != '') {
	    			$scope.line3 = response.data.results['LINE3'];
	    		}
	    		if (response.data.results['FULL'] != null && response.data.results['FULL'] != '') {
	    			$scope.full = response.data.results['FULL'];
	    		}
	    		if ($scope.fast5 != '' && $scope.line1 != '' && $scope.line2 != '' && $scope.line3 != '' && $scope.full != '') {
	    			$scope.resultRes = 'Game Completed';
	    			$scope.stop();
	    		}
	    	}
	    });
	}
	
	$scope.result = function(resultType) {
		$http.get("/api/verifyResult/"+ $scope.customerId+"/"+$scope.roomId+"/"+resultType)
	    .then(function(response) {
	        if ("CONGRATULATIONS" == response.data.status) {
	        	if (resultType =='FAST5') {
	    			$scope.fast5 = $scope.customerName;
	    		} else if (resultType =='LINE1') {
	    			$scope.line1 = $scope.customerName;
	    		} else if (resultType =='LINE2') {
	    			$scope.line2 = $scope.customerName;
	    		}else if (resultType =='LINE3') {
	    			$scope.line3 = $scope.customerName;
	    		}else if (resultType =='FULL') {
	    			$scope.full = $scope.customerName;
	    		}
	        }
	        $scope.resultRes =response.data.status;
	    });
	}
	
	$scope.stopGame = function () {
		$scope.roomSearch=true;
		$scope.roomDetails=false;
		$scope.startGameBut=true;
		$scope.stop();
	}
	$scope.bgColourSelectNo = function (event, value) {
	     //$(event).selected = true;
		var color = $(event.currentTarget).css('background-color');
		console.log('color'+color+":val:"+value+"::"+(value != 'null'));
		if (value != 'null') {
			if (color == 'rgba(0, 0, 0, 0)') {
				$(event.currentTarget).css('background-color','green');
	
			} else if (color == 'rgb(255, 255, 255)') {
				$(event.currentTarget).css('background-color','green');
	
			} else {
				$(event.currentTarget).css('background-color','white');
			}
	   }
		
	}
	$scope.startGame = function() {
		$scope.startGameBut=false;
		$scope.start();
	}
    $scope.start = function() {
    	// stops any running interval to avoid two intervals running at the same time
        if ( angular.isDefined(promise)) {
        	$scope.stop();
        }
	    // store the interval promise
        nextNoButFun();
	    promise = $interval(nextNoButFun, 3000);
    };
    // stops the interval
    // starts the interval
    $scope.stop = function() {
    	if ( angular.isDefined(promise)) {
    		$interval.cancel(promise);
    	}
    };
    $scope.$on('$destroy', function() {
	    // Make sure that the interval is destroyed too
	    $scope.stop();
    });
});