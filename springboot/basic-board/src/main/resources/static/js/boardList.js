$(document).ready(() => {

    const token = localStorage.getItem('accessToken');
    if (!token) {
        window.location.href = '/members/login';
        return;
    }
    setupAjax();
    loadBoard(1);
    $('#logout').click(() => logout());
    $('#searchBtn').on('click', () => loadBoard(1));

    $('#searchResetBtn').on('click', () => {
        $('#searchTitle').val('');
        $('#searchUserId').val('');
        $('#searchFrom').val('');
        $('#searchTo').val('');

        loadBoard(1);
    });

    $('#searchTitle, #searchUserId').on('keydown', (e) => {
        if (e.key === 'Enter') {
            loadBoard(1);
        }
    });
});

const PAGE_SIZE = 10;



const getSearchCondition = () => {

    const condition = {};

    const title = $('#searchTitle').val();
    const userId = $('#searchUserId').val();
    const from = $('#searchFrom').val();
    const to = $('#searchTo').val();

    if (title) {
        condition.title = title;
    }

    if (userId) {
        condition.userId = userId;
    }

    if (from) {
        condition.from = from;
    }

    if (to) {
        condition.to = to;
    }

    return condition;
};

const loadBoard = (page) => {

    const token = localStorage.getItem('accessToken');

    if (!token) {
        window.location.href = '/members/login';
        return;
    }
    console.log("token: ", token)
    $.ajax({
        type: 'GET',
        url: '/api/boards/search',

        headers: {
            Authorization: `Bearer ${token}`
        },

        data: {
            page: page,
            size: PAGE_SIZE,
            ...getSearchCondition()
        },

        success: (response) => {
            renderBoards(response.content);
            renderPagination(page, response.totalPages);
        },

        error: (xhr) => {

            console.error('게시판 조회 실패:', xhr);

            if (xhr.status === 401) {
                localStorage.removeItem('accessToken');
                window.location.href = '/members/login';
                return;
            }

            alert('게시판 데이터를 불러오는데 오류가 발생했습니다.');
        }
    });
};

const renderBoards = (boards) => {

    const $content = $('#boardContent');

    $content.empty();

    if (boards == null || boards.length <= 0) {

        $content.append(
            `
            <tr>
                <td colspan="5" style="text-align: center;">
                    글이 존재하지 않습니다.
                </td>
            </tr>
            `
        );

        return;
    }

    boards.forEach((item) => {

        const author = item.userName
            ? `${item.userName} (${item.userId})`
            : item.userId;

        const commentBadge = item.commentCount > 0
            ? `<span class="comment-count">${item.commentCount}</span>`
            : '-';

        $content.append(
            `
            <tr>
                <td>${item.id}</td>
                <td>
                    <a href="/detail?id=${item.id}">
                        ${item.title}
                    </a>
                </td>
                <td>${author}</td>
                <td>${commentBadge}</td>
                <td>${item.created}</td>
            </tr>
            `
        );
    });
};

const renderPagination = (currentPage, totalPages) => {

    const $pagination = $('#pagination');

    $pagination.empty();

    for (let p = 1; p <= totalPages; p++) {

        const $btn = $(
            `<button class="btn page-btn">${p}</button>`
        );

        if (p === currentPage) {

            $btn.addClass('active');
            $btn.prop('disabled', true);
        }

        $btn.on('click', () => loadBoard(p));

        $pagination.append($btn);
    }
};