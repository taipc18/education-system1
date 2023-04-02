	const app = angular.module("cityzenApp", []);

app.controller("cityzenCtrl", function($scope, $http) {


	//---------------- products --------------------
	$scope.list = [];
	$scope.listfindAllByOrderByIdDesc = [];
	
	$scope.initialize = function(){
		$http.get("/rest/products").then(resp => {
			$scope.list = resp.data;
			console.log(resp.data);
		});
	}
	
	$scope.initializefindAllByOrderByIdDesc = function(){
		$http.get("/rest/products/findAllByOrderByIdDesc").then(resp => {
			$scope.listfindAllByOrderByIdDesc = resp.data;
			console.log(resp.data);
		});
	}
	// ----------------------- favorite ---------------------
	$scope.listFavorite = [];	
	$scope.listFavAll = [];	
	
	
	$scope.favorite = {
		
		initializeFav(){
			var ten = $("#username").text();
			let url = "http://localhost:8080/rest/favorites/layhet/"+ten;
			$http.get(url).then(resp => {
				$scope.listFavAll = resp.data;
			});
		},


		checkIfArrayIsUnique(myArray, id){
			for (var i = 0; i < myArray.length; i++) {
				if (myArray[i] === id) { 
					console.log("nooooo");
					return true;
	        	}
			}
		},

		add(id) {
			var ten = $("#username").text();
			let url = "http://localhost:8080/rest/favorites/layma/" + ten;
			$http.get(url).then(resp => {
				$scope.listFavorite = resp.data;
				if (this.checkIfArrayIsUnique($scope.listFavorite, id)) {
					const Toast = Swal.mixin({
						toast: true,
						position: 'bottom-end',
						showConfirmButton: false,
						timer: 2000,
						timerProgressBar: true,
						didOpen: (toast) => {
							toast.addEventListener('mouseenter', Swal.stopTimer)
							toast.addEventListener('mouseleave', Swal.resumeTimer)
						}
					})
					Toast.fire({
						icon: 'warning',
						title: 'Bạn đã thích sản phẩm này rồi'
					})
				} else {
					$scope.fa = {
						account: { username: ten },
						product: { id: id },
						have: 'true'
					};
					$http.post(`/rest/favorites`, $scope.fa).then(resp => {
						$scope.listFavorite.push(resp.data);
						console.log(resp.data);
						const Toast = Swal.mixin({
							toast: true,
							position: 'bottom-end',
							showConfirmButton: false,
							timer: 1500,
							timerProgressBar: true,
							didOpen: (toast) => {
								toast.addEventListener('mouseenter', Swal.stopTimer)
								toast.addEventListener('mouseleave', Swal.resumeTimer)
							},
							didClose: (toast) => {
								location.href = "/sanpham/chitiet/" + id;
							}
						})
						Toast.fire({
							icon: 'success',
							title: 'Đã thêm vào danh sách yêu thích'
						})

					}).catch(error => {
						console.log("Error", error);
						const Toast = Swal.mixin({
							toast: true,
							position: 'bottom-end',
							showConfirmButton: false,
							timer: 2000,
							timerProgressBar: true,
							didOpen: (toast) => {
								toast.addEventListener('mouseenter', Swal.stopTimer)
								toast.addEventListener('mouseleave', Swal.resumeTimer)
							}
						})
						Toast.fire({
							icon: 'error',
							title: 'Error'
						})
					});
				}
			})
		},

		delete(item) {
			$http.delete(`/rest/favorites/${item.id}`, item).then(resp => {
				var index = $scope.listFavAll.findIndex(p => p.id == item.id);
				$scope.listFavAll.splice(index, 1);
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'success',
					title: 'Delete successfully'
				})
			}).catch(error => {
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'error',
					title: 'Delete Failed'
				})
			});
		}
	}


	$scope.favorite.initializeFav();



	//------------------- news -----------------------
	$scope.listNews = [];

	$scope.initializeNews = function() {
		$http.get("/rest/news").then(resp => {
			$scope.listNews = resp.data;
			console.log(resp.data);
		});
	}

	$scope.initialize();
	$scope.initializeNews();
	$scope.initializefindAllByOrderByIdDesc();


	//-------------------- cart ---------------------------


	$scope.cart = {
		items: [],

		add(id) {
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'success',
					title: 'Đã thêm vào giỏ hàng'
				})
			} else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
					const Toast = Swal.mixin({
						toast: true,
						position: 'bottom-end',
						showConfirmButton: false,
						timer: 1500,
						timerProgressBar: true,
						didOpen: (toast) => {
							toast.addEventListener('mouseenter', Swal.stopTimer)
							toast.addEventListener('mouseleave', Swal.resumeTimer)
						},
						didClose: (toast) => {
							location.href = "/giohang/view/";
						}
					})
					Toast.fire({
						icon: 'success',
						title: 'Đã thêm vào giỏ hàng'
					})
				})
			}
		},

		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
			const Toast = Swal.mixin({
				toast: true,
				position: 'bottom-end',
				showConfirmButton: false,
				timer: 1500,
				timerProgressBar: true,
				didOpen: (toast) => {
					toast.addEventListener('mouseenter', Swal.stopTimer)
					toast.addEventListener('mouseleave', Swal.resumeTimer)
				}
			})
			Toast.fire({
				icon: 'success',
				title: 'Đã xoá sản phẩm'
			})
		},

		clear() {
			this.items = [];
			this.saveToLocalStorage();
			const Toast = Swal.mixin({
				toast: true,
				position: 'bottom-end',
				showConfirmButton: false,
				timer: 1500,
				timerProgressBar: true,
				didOpen: (toast) => {
					toast.addEventListener('mouseenter', Swal.stopTimer)
					toast.addEventListener('mouseleave', Swal.resumeTimer)
				}
			})
			Toast.fire({
				icon: 'success',
				title: 'Đã xoá toàn bộ sản phẩm'
			})
		},

		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},

		get amount() {
			return this.items
				.map(item => item.qty * item.price)
				.reduce((total, qty) => total += qty, 0);
		},

		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},

		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$scope.cart.loadFromLocalStorage();

	$scope.order = {

		createDate: new Date(),
		address: "",
		status: "Chờ xác nhận",
		account: { username: $("#username").text() },
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			});
		},
		
		trusoluong(id){
			$http.put(`/rest/products/trusoluong/${id}`).then(resp => {
				console.log(resp);
			}).catch(error => {
				alert("error");
				console.log(error)
			})
		},
		
		purchase() {
			let address =document.getElementById('address').value;
			if(address.length===0){
			Swal.fire({
				toast:true,
				position: 'bottom-end',
				icon: 'error',
				title: 'Không được để trống địa chỉ người nhận',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
			var order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					},
					didClose: (toast) => {
						$scope.cart.clear();
				$scope.order.trusoluong(resp.data.id);
				location.href = "/dathang/chitiet/" + resp.data.id;
					}

				})
				Toast.fire({
					icon: 'success',
					title: 'Đặt hàng thành công'
				})
				
			}).catch(error => {
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'error',
					title: 'Đặt hàng thất bại'
				})
				console.log(error)
			})
		}
	}

	//--------------------- new review -----------------------

	$scope.reviews = [];
	
	$scope.initializeReviews = function(){
		$http.get("/rest/reviews").then(resp => {
			$scope.reviews = resp.data;
			console.log(resp.data);
		});
	}

	$scope.initializeReviews();
	
	$scope.validateReview=function(){
		let subject =document.getElementById('subject').value;
		
		if(subject.length===0){
			Swal.fire({
				toast:true,
				position: 'bottom-end',
				icon: 'error',
				title: 'Không được để trống đánh giá',
				showConfirmButton: false,
				timer: 2500
			  })			
			return false;
		}
		else{
			$scope.review.create();
		}
	}

	$scope.review = {
		review:"",
		account:{username:$("#username").text()},
		
		
		
		create(){
			var review = angular.copy(this);
			$http.post(`/rest/reviews`, review).then(resp => {
				resp.data.createDate = new Date(resp.data.createDate);
				$scope.reviews.push(resp.data);
				this.review="";
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'success',
					title: 'Review successfully'
				})
			}).catch(error => {
				console.log(error);
			})
		},

		reset() {
			this.review = "";
		}
	}

	// --------------------- reviews sản phẩm ---------------------

	$scope.productreviews = [];


	$scope.productreview = {
		review:"",
		account:{username:$("#username").text()},
		product:{id:$("#productid").text()},
		create(){
			var review = angular.copy(this);
			$http.post(`/rest/productreviews`, review).then(resp => {
				resp.data.createDate = new Date(resp.data.createDate);
				$scope.productreviews.push(resp.data);
				this.review="";
				const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					},
					didClose: (toast) => {
						location.href = "/sanpham/chitiet/" + resp.data.product.id;
					}
				})
				Toast.fire({
					icon: 'success',
					title: 'Review successfully'
				})

				}).catch(error => {
				console.log(error);
			})
		},

		reset() {
			this.review = "";
		}
	}
	
	$scope.kiemloi = function(){
		var soluongdat  = document.getElementById('soluongdat').value;
		var soluonghang = Number(document.getElementById('soluonghang').innerText);	
		if(Number(soluongdat)>soluonghang){
		 	const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'error',
					title: 'Vượt quá số lượng hàng'
				})
				return false;
		}
		if(soluongdat===""){
		 	const Toast = Swal.mixin({
					toast: true,
					position: 'bottom-end',
					showConfirmButton: false,
					timer: 2000,
					timerProgressBar: true,
					didOpen: (toast) => {
						toast.addEventListener('mouseenter', Swal.stopTimer)
						toast.addEventListener('mouseleave', Swal.resumeTimer)
					}
				})
				Toast.fire({
					icon: 'error',
					title: 'Vui lòng thêm số lượng'
				})
				return false;
		}
		else{
			location.href = "/dathang/checkout";
		}
	}

	//--------------------- products page ------------------------

	$scope.pager = {
		page: 0,
		size: 8,
		get list() {
			var start = this.page * this.size;
			return $scope.list.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.list.length / this.size)
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}


	//--------------------- news page ------------------------

	$scope.pagerNews = {
		page: 0,
		size: 2,
		get listNews() {
			var start = this.page * this.size;
			return $scope.listNews.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.listNews.length / this.size)
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

	//--------------------- reviews page ------------------------

	$scope.pagerReviews = {
		page: 0,
		size: 5,
		get listReviews() {
			var start = this.page * this.size;
			return $scope.reviews.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.reviews.length / this.size)
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}


	let url = window.location.pathname;
	if(url.includes("saukhithanhtoan")) {
		$scope.cart.clear();
	}
	
	
	
});