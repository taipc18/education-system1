app.controller("product-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};
	$scope.actis = [];
	
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.q = '';

	$scope.initialize = function() {
		$http.get("/rest/products/findAllByOrderByIdDesc").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		});
	}	

	$scope.initialize();
	

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'noimg.jpg',
			category:{ id: 'ANT' },
			available: true
		};
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	}



	$scope.validateCreate=function(){
		let name =document.getElementById('name').value;
		let price =document.getElementById('price').value;
		let short =document.getElementById('short').value;
		let quantity = document.getElementById('quantity').value;
		quantity = Number(quantity);
		let description =document.getElementById('description').value;
		var pattern = /^(?:-(?:[1-9](?:\d{0,2}(?:\d{3})+|\d*))|(?:0|(?:[1-9](?:\d{0,2}(?:\d{3})+|\d*))))(?:.\d+|)$/;
		var testprice = pattern.test(price);
		if(name.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(!testprice){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Định dạng giá không đúng',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(quantity < 0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Số lượng phải lớn hơn 0 ',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(price.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống giá sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(short.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tiêu đề sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(description.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống mô tả sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		
		else{
			$scope.create();
		}
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/products`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.createActi();
			$scope.reset();
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
				title: 'Created successfully'
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
				title: 'Create failed'
			})
			console.log("Error", error);
		});
	}


	$scope.createActi = function() {
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate: new Date(),
			activity: ten + ' vừa thêm sản phẩm mới'
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});
	}



	$scope.validateUpdate=function(){
		let name =document.getElementById('name').value;
		let price =document.getElementById('price').value;
		let short =document.getElementById('short').value;
		let quantity = document.getElementById('quantity').value;
		quantity = Number(quantity);
		let description =document.getElementById('description').value;
		var pattern = /^(?:-(?:[1-9](?:\d{0,2}(?:\d{3})+|\d*))|(?:0|(?:[1-9](?:\d{0,2}(?:\d{3})+|\d*))))(?:.\d+|)$/;
		var testprice = pattern.test(price);
		if(name.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(!testprice){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Định dạng giá không đúng',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(quantity < 0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Số lượng phải lớn hơn 0',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(price.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống giá sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(short.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tiêu đề sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(description.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống mô tả sản phẩm',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}

		
		else{
			$scope.update();
		}
	}


	$scope.update = function() {
		var item = angular.copy($scope.form);

		var tenupdate = item.name;
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate: new Date(),
			activity: ten + ' vừa chỉnh sửa sản phẩm ' + tenupdate
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});



		$http.put(`/rest/products/${item.id}`, item).then(resp => {
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
				title: 'Update failed'
			})
			console.log("Error", error);
		});
	}


	$scope.delete = function(item) {
		var tendelete = item.name;
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate: new Date(),
			activity: ten + ' vừa xóa sản phẩm ' + tendelete
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});

		$http.delete(`/rest/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
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
				title: 'Deleted successfully'
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
				title: 'Delete failed'
			})
			console.log("Error", error);
		});
	}




	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/productimg', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("error");
			console.log("Error", error);
		})
		$http.post("/rest/save/productimg", data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined },
			enctype: "multipart/form-data"
		})
	}
	
	$scope.imageChanged1 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/productimg', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image1 = resp.data.name;
		}).catch(error => {
			alert("error");
			console.log("Error", error);
		})
		$http.post("/rest/save/productimg", data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined },
			enctype: "multipart/form-data"
		})
	}
	$scope.imageChanged2 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/productimg', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image2 = resp.data.name;
		}).catch(error => {
			alert("error");
			console.log("Error", error);
		})
		$http.post("/rest/save/productimg", data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined },
			enctype: "multipart/form-data"
		})
	}
	$scope.imageChanged3 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/productimg', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image3 = resp.data.name;
		}).catch(error => {
			alert("error");
			console.log("Error", error);
		})
		$http.post("/rest/save/productimg", data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined },
			enctype: "multipart/form-data"
		})
	}


	$scope.sort = function(){
		
	}
	
	$scope.sortProperty='id';
	
	$scope.currentPage = 0;
	$scope.pageSize = '10';
	
	$scope.numberOfPage = function(){
		return Math.ceil($scope.items.length / $scope.pageSize);
	}
	   
	for(let i = 0; i <= 45; i++){
		$scope.items.push("Items" + i);
	}
	
	$scope.pagination = function(){
		return $scope.currenPage = 0;
	}

});

app.filter('startFrom', function() {
    return function(input, start) {
        start = +start;
        return input.slice(start);
    }
});