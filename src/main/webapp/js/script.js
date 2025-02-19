// TODO: write requests for other buttons or actions like: restart, logout, register etc.

$(document).ready(function () {
    $('#startTestQuestButton').click(function () {
        $.ajax({
            url: '/start',
            type: 'GET',
            success: function (response) {
                console.log('Success callback triggered');
                console.log('Response:', response);
                window.location.href = '/game.jsp';
            },
            error: function (response) {
                if (response.status === 401) {
                    alert('Please login to start the test');
                    window.location.href = '/register.jsp';
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