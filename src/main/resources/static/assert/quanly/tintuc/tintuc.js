app.controller("news-ctrl", function($scope, $http){
	$scope.newss = [];
	$scope.form = {};
	
	$scope.initialize = function(){
		$http.get("/rest/news").then(resp => {
			$scope.newss = resp.data;
			console.log(resp.data);
		});
	}
	
	$scope.initialize();
	
	$scope.reset = function(){
		$scope.form = {
			createDate:new Date(),
			image:'noimg.jpg',
		};
	}
	
	$scope.edit = function(news){
		$scope.form = angular.copy(news);
		$(".nav-tabs a:eq(0)").tab('show')
	}

$scope.validateCreate=function(){
		let write =document.getElementById('write').value;
		let newsname =document.getElementById('newsname').value;
		let description =document.getElementById('description').value;
		let opening =document.getElementById('opening').value;
		let body1 =document.getElementById('body1').value;
		let body2 =document.getElementById('body2').value;
		let ending = document.getElementById('ending').value;
		
		if(write.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên người viết',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(newsname.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên tin tức', 
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
				title: 'Không được để trống tiêu đề',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(opening.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống mở đoạn',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(body1.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống thân đoạn 1',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(body2.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống thân đoạn 2',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(ending.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống kết bài',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		else{
			$scope.create();
		}
	}
	// 
	$scope.create = function(){
		var news = angular.copy($scope.form);
		$http.post(`/rest/news`, news).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.newss.push(resp.data);
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
				title: 'Create Failed'
			})
			console.log("Error", error);
		});
	}
	
	$scope.createActi = function(){
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate:new Date(),
			activity:ten + ' vừa thêm một tin tức mới'
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});
	}
	
	
	$scope.validateUpdate=function(){
		let write =document.getElementById('write').value;
		let newsname =document.getElementById('newsname').value;
		let description =document.getElementById('description').value;
		let opening =document.getElementById('opening').value;
		let body1 =document.getElementById('body1').value;
		let body2 =document.getElementById('body2').value;
		let ending = document.getElementById('ending').value;
		
		if(write.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên người viết',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(newsname.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên tin tức', 
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
				title: 'Không được để trống tiêu đề',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(opening.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống mở đoạn',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(body1.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống thân đoạn 1',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(body2.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống thân đoạn 2',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		if(ending.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống kết bài',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		else{
			$scope.update();
		}
	}
	//  
	$scope.update = function(){
		var news = angular.copy($scope.form); // DUNG ANGULAR  LAM CHUC NANG UPDATE ,KHI UP LEN DAN DEN
		
		// THEM LICH SU HOAT DONG
		var tenupdate = news.name;
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate:new Date(),
			activity:ten + ' vừa chỉnh sửa tin tức ' + tenupdate
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});
		
		
		//  roi thuc thi ham` PUT cua3 restCrl, 
		$http.put(`/rest/news/${news.id}`, news).then(resp => {
			var index = $scope.newss.findIndex(p => p.id == news.id);
			$scope.newss[index] = news;
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
	
	
	
	// 
	$scope.delete = function(news){
		var tendelete = news.name;
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate:new Date(),
			activity:ten + ' vừa xóa tin tức ' + tendelete
		};
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});
		
		
		$http.delete(`/rest/news/${news.id}`, news).then(resp => {
			var index = $scope.newss.findIndex(p => p.id == news.id);
			$scope.newss.splice(index, 1);
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
				title: 'Delete Failed'
			})
			console.log("Error", error);
		});
	}
	
	
	$scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/productimg', data, {
			transformRequest:angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("error");
			console.log("Error", error);
		})
		$http.post("/rest/save/productimg", data, {
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined},
			enctype: "multipart/form-data"
		})
	}
	
	$scope.pager = {
		page:0,
		size:7,
		get newss(){
			var start = this.page * this.size;
			return $scope.newss.slice(start, start + this.size);
		}, 
		get count(){
			return Math.ceil(1.0 * $scope.newss.length / this.size)
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
		}
	}
});