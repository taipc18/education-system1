app.controller("orders-ctrl", function($scope, $http){
	$scope.items = [];
	$scope.form = {};
	$scope.sta = [];
	$scope.sortStatus = function(){
		
	}
	$scope.ods = [];
	$scope.initialize = function(){
		$http.get("/rest/orders").then(resp => {
			$scope.items = resp.data;
			console.log(resp.data);
		});
		$http.get("/rest/statuss").then(resp => {
			$scope.sta = resp.data;
		});
	}

	$scope.initialize();

	$scope.edit = function(item){
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
		let url = "http://localhost:8080/api/orders/" + $scope.form.id;
		$http.get(url).then(resp => {
			$scope.ods = resp.data;
			console.log($scope.ods);
		})
	}
	
	
	$scope.validateUpdate=function(){
		let address =document.getElementById('address').value;
		
		if(address.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống địa chỉ người nhận',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		else{
			$scope.update();
		}
	}
	
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		
		
		var tenupdate = item.id;
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate:new Date(),
			activity:ten + ' vừa chỉnh sửa đơn hàng ' + tenupdate
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});
		
		
		
		$http.put(`/rest/orders/${item.id}`, item).then(resp => {	
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			const Toast = Swal.mixin({
				toast: true,
				position: 'bottom-end',
				showConfirmButton: false,
				timer: 3500,
				timerProgressBar: true,
				didOpen: (toast) => {
					toast.addEventListener('mouseenter', Swal.stopTimer)
					toast.addEventListener('mouseleave', Swal.resumeTimer)
				},
				didClose: (toast) => {
					location.reload()
				}
			})
			Toast.fire({
				icon: 'success',
				title: 'Updated successfully'
			})
		}).catch(error => {
			const Toast = Swal.mixin({
				toast: true,
				position: 'bottom-end',
				showConfirmButton: false,
				timer: 3500,
				timerProgressBar: true,
				didOpen: (toast) => {
					toast.addEventListener('mouseenter', Swal.stopTimer)
					toast.addEventListener('mouseleave', Swal.resumeTimer)
				},
				didClose: (toast) => {
					location.reload()
				}
			})
			Toast.fire({
				icon: 'error',
				title: 'Update Failed'
			})
			console.log("Error", error);
		});
	}
	
	
	
	
	
	
	
	
	$scope.pager = {
		page:0,
		size:7,
		statusProperty: 'dahuy',
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		}, 
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size)
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		},
		get sortStatus() {
		}
	}
	
});