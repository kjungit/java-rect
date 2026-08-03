$(document).ready(() => {

    $('#signin').click(() => {

        const formData = {
            userId: $('#user_id').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/members/login',
            data: JSON.stringify(formData),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',

            success: (response) => {
                console.log('로그인 응답:', response);
                console.log('accessToken:', response.accessToken);
                localStorage.setItem(
                    'accessToken',
                    response.accessToken
                );
                console.log(
                    '저장된 accessToken:',
                    localStorage.getItem('accessToken')
                );
                alert(response.message);
                window.location.href = response.url;
            },
            error: (xhr) => {
                const response = xhr.responseJSON;
                alert(
                    response && response.message
                        ? response.message
                        : '로그인 중 오류가 발생했습니다.'
                );
            }
        });
    });
});