document.addEventListener("DOMContentLoaded", function () {
  const orderTable = document.getElementById("orderTable");
  const orderTbody = orderTable.querySelector("tbody");
  const orderRows = Array.from(orderTbody.getElementsByTagName("tr"));
  const orderItemsPerPage = 3;
  const orderTotalPages = Math.ceil(orderRows.length / orderItemsPerPage);
  let orderCurrentPage = 1;

  function showOrderPage(page) {
    const orderStartIndex = (page - 1) * orderItemsPerPage;
    const orderEndIndex = orderStartIndex + orderItemsPerPage;

    orderRows.forEach((row, index) => {
      if (index >= orderStartIndex && index < orderEndIndex) {
        row.style.display = "";
      } else {
        row.style.display = "none";
      }
    });
  }

  function createOrderPagination() {
    const orderPaginationDiv = document.getElementById("orderPagination");

    for (let i = 1; i <= orderTotalPages; i++) {
      const button = document.createElement("button");
      button.innerText = i;
      button.addEventListener("click", function () {
        orderCurrentPage = i;
        showOrderPage(orderCurrentPage);
      });
      orderPaginationDiv.appendChild(button);
    }
  }

  showOrderPage(orderCurrentPage);
  createOrderPagination();

  // Đối với bảng khách hàng
  const customerTable = document.getElementById("customerTable");
  const customerTbody = customerTable.querySelector("tbody");
  const customerRows = Array.from(customerTbody.getElementsByTagName("tr"));
  const customerItemsPerPage = 3;
  const customerTotalPages = Math.ceil(customerRows.length / customerItemsPerPage);
  let customerCurrentPage = 1;

  function showCustomerPage(page) {
    const customerStartIndex = (page - 1) * customerItemsPerPage;
    const customerEndIndex = customerStartIndex + customerItemsPerPage;

    customerRows.forEach((row, index) => {
      if (index >= customerStartIndex && index < customerEndIndex) {
        row.style.display = "";
      } else {
        row.style.display = "none";
      }
    });
  }

  function createCustomerPagination() {
    const customerPaginationDiv = document.getElementById("customerPagination");

    for (let i = 1; i <= customerTotalPages; i++) {
      const button = document.createElement("button");
      button.innerText = i;
      button.addEventListener("click", function () {
        customerCurrentPage = i;
        showCustomerPage(customerCurrentPage);
      });
      customerPaginationDiv.appendChild(button);
    }
  }

  showCustomerPage(customerCurrentPage);
  createCustomerPagination();
  
  
  const accountTable = document.getElementById("accountTable");
const accountTableTbody = accountTable.querySelector("tbody");
const accountTableRows = Array.from(accountTableTbody.getElementsByTagName("tr"));
const accountTableItemsPerPage = 3;  // Số lượng dòng trên mỗi trang
const accountTableTotalPages = Math.ceil(accountTableRows.length / accountTableItemsPerPage);

let accountCurrentPage = 1;

function showAccountPage(page) {
    const accountStartIndex = (page - 1) * accountTableItemsPerPage;
    const accountEndIndex = accountStartIndex + accountTableItemsPerPage;

    accountTableRows.forEach((row, index) => {
        if (index >= accountStartIndex && index < accountEndIndex) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}

function createAccountPagination() {
    const accountPaginationDiv = document.getElementById("accountPagination");

    for (let i = 1; i <= accountTableTotalPages; i++) {
        const button = document.createElement("button");
        button.innerText = i;
        button.addEventListener("click", function () {
            accountCurrentPage = i;
            showAccountPage(accountCurrentPage);
        });
        accountPaginationDiv.appendChild(button);
    }
}

// Hiển thị trang đầu tiên và tạo phân trang
showAccountPage(accountCurrentPage);
createAccountPagination();
});

