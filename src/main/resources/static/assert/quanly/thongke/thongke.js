app.controller("thongke-ctrl", function($scope, $http){
	
	$scope.productlist = [];
	$scope.reviewlist = [];
	$scope.orderlist = [];
	$scope.sta = [];
	$scope.cates = [];
	$scope.newss = [];
	$scope.activities = [];
	
	
// -----------------------tong tien ----------------------------

	$scope.tongtien = 0;
	
	$scope.tong = function(){
		$http.get("/api/orders/hientong").then(resp => {
			if(resp.data>0){
			$scope.tongtien = resp.data;
			console.log(resp.data);
			}else{
			$scope.tongtien = 0;
			}
		});
	}
	
	$scope.tong();
	
	
	
	
	
// ----------------------- doanh thu chart ----------------------------


	$scope.doanhthucate = [];
	
	$scope.doanhthucate = function(){
		$http.get("/rest/categories/DoanhThuTheoLoai").then(resp => {
			$scope.doanhthucate = resp.data;
			console.log(resp.data);
			var myChart = document.getElementById('myChart').getContext('2d');
			var myChart = new Chart( myChart,
					{
						type : 'bar',
						data : {
							labels : [ 'Chibi', 
									   'Figure',
									   'Gundam', 
									   'Khác',
									   'Lego' ],
							datasets : [ {
								label : 'Earning',
								data : [ $scope.doanhthucate[0], 
										 $scope.doanhthucate[1],
										 $scope.doanhthucate[2],
										 $scope.doanhthucate[3],
										 $scope.doanhthucate[4] ],
								backgroundColor : [
										'rgba(255, 99, 132, 1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(153, 102, 255, 1)',
										'rgba(255, 159, 64, 1)' ],
			
								borderWidth : 1
							} ]
						},
						options : {
							responsive : true,
						}
					});
		});
	}
	
	$scope.doanhthucate();
	


// ----------------------- so luong chart ----------------------------


	$scope.soluongcate = [];
	
	$scope.soluongcate = function(){
		$http.get("/rest/categories/SoLuongTheoLoai").then(resp => {
			$scope.soluongcate = resp.data;
			console.log(resp.data);
			var earning = document.getElementById('earning').getContext('2d');
			var earning = new Chart(earning, {
				type : 'polarArea',
				data : {
					labels : [ 'Chibi', 
							   'Figure',
							   'Gundam', 
							   'Khác',
							   'Lego' ],
					datasets : [ {
						label : 'Tracffic Source',
						data : [ $scope.soluongcate[0], 
								 $scope.soluongcate[1],
								 $scope.soluongcate[2],
								 $scope.soluongcate[3],
								 $scope.soluongcate[4] ],
						backgroundColor : [
								'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 40, 40)',
								'rgba(54, 162, 235, 1)',
								'rgba(154, 162, 23, 1)',
								'rgba(255, 206, 86, 1)' ],
	
					} ]
				},
				options : {
					responsive : true,
				}
			});
		});
	}
	
	$scope.soluongcate();
	



//------------------------- list ----------------------------	
	$scope.productinitialize = function(){
		$http.get("/rest/products/findAllByOrderByIdDesc").then(resp => {
			$scope.productlist = resp.data;
			console.log(resp.data);
		});
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});
	}
	
	$scope.reviewinitialize = function(){
		$http.get("/rest/reviews").then(resp => {
			$scope.reviewlist = resp.data;
			console.log(resp.data);
		});
	}
	
	$scope.orderinitialize = function(){
		$http.get("/rest/orders").then(resp => {
			$scope.orderlist = resp.data;
			console.log(resp.data);
		});
		$http.get("/rest/statuss").then(resp => {
			$scope.sta = resp.data;
		});
	}
	
	$scope.newsinitialize = function(){
		$http.get("/rest/news").then(resp => {
			$scope.newss = resp.data;
			console.log(resp.data);
		});
	}
	
	$scope.activitiesinitialize = function(){
		$http.get("/rest/activities").then(resp => {
			$scope.activities = resp.data;
			console.log(resp.data);
		});
	}

//------------------------- run -------------------------
	
	$scope.productinitialize();
	$scope.reviewinitialize();
	$scope.orderinitialize();
	$scope.newsinitialize();
	$scope.activitiesinitialize();
	
//--------------------- count ----------------------------
	
	$scope.tongSanpham = {
		size:1, 
		get count(){
			return Math.ceil(1.0 * $scope.productlist.length / this.size)
		}
	}
	
	$scope.tongDanhgia = {
		size:1, 
		get count(){
			return Math.ceil(1.0 * $scope.reviewlist.length / this.size)
		}
	}
	
	$scope.tongDonhang = {
		size:1, 
		get count(){
			return Math.ceil(1.0 * $scope.orderlist.length / this.size)
		}
	}
	



//---------------------------------- pages --------------------------------------

	$scope.pagerProducts = {
		page:0,
		size:7,
		get items(){
			var start = this.page * this.size;
			return $scope.productlist.slice(start, start + this.size);
		}
	}
	
	$scope.pagerOrders = {
		page:0,
		size:7,
		get items(){
			var start = this.page * this.size;
			return $scope.orderlist.slice(start, start + this.size);
		}
	}
	
	$scope.pagerNewss = {
		page:0,
		size:3,
		get items(){
			var start = this.page * this.size;
			return $scope.newss.slice(start, start + this.size);
		}
	}
	
	$scope.pagerActivities = {
		page:0,
		size:7,
		get items(){
			var start = this.page * this.size;
			return $scope.activities.slice(start, start + this.size);
		}
	}
	
	

	
});