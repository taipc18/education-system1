    // MenuToggle 
    let toggle = document.querySelector('.toggle')
    let navigation = document.querySelector('.navigation')
    let main = document.querySelector('.main')
    toggle.onclick=function(){
        navigation.classList.toggle('active')
        main.classList.toggle('active')
    }
    //Menu show 
    // menu 
    const menu = document.querySelector(".navigation")
    const menuButton = document.querySelector(".navbar__icons")

    menuButton.addEventListener('click',()=>{
        menu.classList.toggle("navbar__open");
        menuButton.classList.toggle('open');
})
    //add hovered class in selected list item
    let list = document.querySelectorAll('.navigation li');
    function activeLink(){
        list.forEach((item) =>
        item.classList.remove('hovered'));
        this.classList.add('hovered')
    }
        list.forEach((item) =>
        item.addEventListener('mouseover',activeLink));
        
    // add chart
    var ctx = document.getElementById('myChart').getContext('2d');
    var earning = document.getElementById('earning').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'polarArea',
        data: {
            labels: ['Gumdam', 'Lego','Siêu Nhân'],
            datasets: [{
                label: 'Tracffic Source',
                data: [20000, 19000, 30000],
                backgroundColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)'
                ],
                
               
            }]
        },
        options: {
            responsive:true,
    }
});    
var myChart = new Chart(earning, {
    type: 'bar',
    data: {
        labels: ['2017', '2018', '2019', '2020', '2021', '2022'],
        datasets: [{
            label: 'Earning',
            data: [77000, 10000, 30000, 7500, 70000, 55000],
            backgroundColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                
                'rgba(153, 102, 255, 1)',
                'rgb(46, 204, 113,1)',
                'rgba(255, 159, 64, 1)'
            ],
           
            borderWidth: 1
        }]
    },
    options: {
        responsive:true,
    }
});

function myFunction() {
    const togglePassword = document.querySelector("#togglePassword");
    var x = document.getElementById("myInput");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
    togglePassword.addEventListener("click", function () {
        // toggle the type attribute
        const type = password.getAttribute("type") === "password" ? "text" : "password";
        password.setAttribute("type", type);
        
        // toggle the icon
        this.classList.toggle("bi-eye");
        
    });

  }