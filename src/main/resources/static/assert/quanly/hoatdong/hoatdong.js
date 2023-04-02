app.controller("activities-ctrl", function($scope, $http){
	$scope.activities = [];
	$scope.form = {};
	
	$scope.initialize = function(){
		$http.get("/rest/activities").then(resp => {
			$scope.activities = resp.data;
			console.log(resp.data);
		});
	}
	
	$scope.initialize();
	

	
	$scope.pager = {
		page:0,
		size:10,
		get activities(){
			var start = this.page * this.size;
			return $scope.activities.slice(start, start + this.size);
		}, 
		get count(){
			return Math.ceil(1.0 * $scope.activities.length / this.size)
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