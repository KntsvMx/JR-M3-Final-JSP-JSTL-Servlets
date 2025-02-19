// TODO: write requests for other buttons or actions like: restart, logout, register etc.

$(document).ready(function () {
    $('#startTestQuestButton').click(function () {
        $.ajax({
            url: '/start',
            type: 'GET',
            success: function () {
                console.log('Success callback triggered');
                window.location.href = '/register.jsp';
            },
            error: function (response) {
                if (response.status === 401) {
                    console.log('Redirecting to register page');
                } else {
                    console.error('Error: ' + response.responseText);
                    alert('Failed to start the test');
                }
            }
        });
    });
    $('#registerNewPlayer').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: '/register',
            type: 'POST',
            data: {
                username: $('#username').val(),
            },
            success: function (response) {
                console.log('Success callback triggered');
                window.location.href = '/start';
            },
            error: function (response) {
                console.error('Error: ' + response.responseText);
                alert('Failed to register');
            }
        })
    });

    //
    // $('#logout').click(function () {
    //     logout();
    // });
    //
    // $('#restart').click(function () {
    //     restart();
    // });


});