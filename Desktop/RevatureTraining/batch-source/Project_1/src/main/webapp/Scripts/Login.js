$(function () {
    $('#login-form').on('submit',function (e) {
        e.preventDefault();
        var data = $(this).serializeArray();
        console.log(data);
        $.ajax({
            type:'post',
            url : 'http://localhost:8080/login',
            data:data,
            success:function (response) {
                let emp = JSON.parse(response);
                console.log(emp);
                let roleId = parseInt(emp.data.roleId);
                console.log(roleId);
                if (roleId===1){
                    location.href="http://localhost:8080/reimbursement";
                    localStorage.setItem("session",true);
                    localStorage.setItem("data",JSON.stringify(emp.data));
                }
                else{
                    location.href="http://localhost:8080/reimbursement";
                    localStorage.setItem("session",true);
                    localStorage.setItem("data",JSON.stringify(emp.data));
                }
            },
            error:function (err) {
                if (err.status===401){
                    console.log("Invalid Login");
                }
            }
        });
    });
});