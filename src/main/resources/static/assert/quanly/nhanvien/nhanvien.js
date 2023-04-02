app.controller("authority-ctrl", function($scope, $http, $location){
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];
	
	
	
	$scope.initialize = function(){
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})
		
		$http.get("/rest/accounts?admin=true").then(resp => {
			$scope.admins = resp.data;
		})
		
		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
		}).catch(error => {
			$location.path("/unauthorized");
			console.log(error);
		})
	}
	
	$scope.initialize();
	
	
	$scope.edit = function(user){
		$scope.form = angular.copy(user);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	

	$scope.validateUpdate=function(){
		let fullname =document.getElementById('fullname').value;
		let email =document.getElementById('email').value;
		let password =document.getElementById('password').value;
		let phonenumber =document.getElementById('phonenumber').value;
		var patternemail =/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
		var patternpassword =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
		var patternphonenumber=/(84|0[3|5|7|8|9])+([0-9]{8})\b/;
		var testemail = patternemail.test(email);
		var testpassword = patternpassword.test(password);
		var testphonenumber = patternphonenumber.test(phonenumber);
		if(fullname.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống tên nhân viên',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		
		if(email.length===0){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Không được để trống email',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		
		if(!testemail){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Định dạng email không hợp lệ ',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		
		if(!testpassword){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Mật khẩu phải có ít nhất 8 kí tự, 1 chữ thường, 1 chữ hoa và 1 số',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		
		if(!testphonenumber){
			Swal.fire({
				toast:true,
				position: 'top',
				icon: 'error',
				title: 'Định dạng số điện thoại không hợp lệ ',
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
		var user = angular.copy($scope.form);
		
		
		var tenupdate = user.username;
		var ten = document.getElementById("empl").innerText;
		$scope.acti = {
			createDate:new Date(),
			activity:ten + ' vừa cập nhật nhân viên ' + tenupdate
		};
		
		$http.post(`/rest/activities`, $scope.acti).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.actis.push(resp.data);
		}).catch(error => {
			console.log("Error", error);
		});
		
		
		
		$http.put(`/rest/accounts/${user.username}`, user).then(resp => {
			var index = $scope.admins.indexOf(p => p.id == user.username);
			$scope.admins[index] = user;
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
	
	
	
	
	
	
	
	
	
	
	$scope.authority_of = function(acc, role){
		if($scope.authorities){
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}
	
	$scope.authority_changed = function(acc, role){
		var authority = $scope.authority_of(acc, role);
		if(authority){
			$scope.revoke_authority(authority);
		}else{
			authority = {account:acc, role:role};
			$scope.grant_authority(authority);
		}
	}
	
	$scope.grant_authority = function(authority){
		$http.post(`/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data);
			alert("Success");
		}).catch(error => {
			alert("Error");
			console.log("error", error);
		})
	}
	
	
	$scope.revoke_authority = function(authority){
		$http.delete(`/rest/authorities/${authority.id}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index, 1);
			alert("Success");
		}).catch(error => {
			alert("Error");
			console.log("error", error);
		})
	}
});