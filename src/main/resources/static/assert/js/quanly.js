app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/thongke", {
		templateUrl:"/assert/quanly/thongke/index.html",
		controller:"thongke-ctrl"
	})
	.when("/sanpham", {
		templateUrl:"/assert/quanly/sanpham/index.html",
		controller:"product-ctrl"
	})
	.when("/nhanvien", {
		templateUrl:"/assert/quanly/nhanvien/index.html",
		controller:"authority-ctrl"
	})
	.when("/donhang", {
		templateUrl:"/assert/quanly/donhang/index.html",
		controller:"orders-ctrl"
	})
	.when("/tintuc", {
		templateUrl:"/assert/quanly/tintuc/index.html",
		controller:"news-ctrl"
	})
	.when("/hoatdong", {
		templateUrl:"/assert/quanly/hoatdong/index.html",
		controller:"activities-ctrl"
	})
	.otherwise({
		templateUrl:"/assert/quanly/thongke/index.html",
		controller:"thongke-ctrl"
	});
})