<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.example.ecommerce.models.Product" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style type="text/css">
    label {
      display: block;
      margin-top: 10px; /* Adjust margin for spacing between labels and inputs */
    }
  </style>
  <title>ADD PRODUCTS</title>
</head>
<body class="bg-light">

<%
  PrintWriter out1 = response.getWriter();
  String error = (String) request.getAttribute("error");
  if (error != null) {
    out1.println("<html><body>");
    out1.println("<b>" + error + "</b>");
    out1.println("</body></html>");
  }
  Product editProduct = (Product) request.getAttribute("edit-product");
  if (editProduct != null) {
%>
<form method="post" action="product?edit=product" class="container mt-5">
  <div class="mb-3">
    <label for="product-name" class="form-label">Product Name:</label>
    <label>
      <input type="text" class="form-control"  name="product-name" value="<%= editProduct.getName() %>">
    </label>
  </div>
  <div class="mb-3">
    <label for="product-price" class="form-label">Product Price:</label>
    <label>
      <input type="text" class="form-control"  name="product-price" value="<%= editProduct.getPrice() %>">
    </label>
  </div>
  <div class="mb-3">
    <label for="product-quantity" class="form-label">Product Quantity:</label>
    <label>
      <input type="text" class="form-control"  name="product-quantity" value="<%= editProduct.getQuantity() %>">
    </label>
  </div>
  <div class="mb-3">
    <label for="product-category" class="form-label">Product Category:</label>
    <label>
      <input type="text" class="form-control"  name="product-category" value="<%= editProduct.getCategory() %>">
    </label>
  </div>
  <div class="mb-3">
    <label for="product-image" class="form-label">Product Image:</label>
    <label>
      <input type="text" class="form-control"  name="product-image" value="<%= editProduct.getImage() %>">
    </label>
  </div>
  <input type="hidden" name="id" value="<%= editProduct.getId()%>">
  <button type="submit" class="btn btn-primary">Edit Product</button>
</form>

<%
} else {
%>
<form method="post" action="product?admin=product" class="container mt-5">
  <div class="mb-3">
    <label for="product-name" class="form-label">Product Name:</label>
    <input type="text" class="form-control" id="product-name" name="product-name">
  </div>
  <div class="mb-3">
    <label for="product-price" class="form-label">Product Price:</label>
    <input type="text" class="form-control" id="product-price" name="product-price">
  </div>
  <div class="mb-3">
    <label for="product-quantity" class="form-label">Product Quantity:</label>
    <input type="text" class="form-control" id="product-quantity" name="product-quantity">
  </div>
  <div class="mb-3">
    <label for="product-category" class="form-label">Product Category:</label>
    <input type="text" class="form-control" id="product-category" name="product-category">
  </div>
  <div class="mb-3">
    <label for="product-image" class="form-label">Product Image:</label>
    <input type="text" class="form-control" id="product-image" name="product-image">
  </div>
  <button type="submit" class="btn btn-success">Add Product</button>
</form>
<%
  }
%>
<%--<a href="product?admin=view-product" style="margin-left: 4rem" class="btn btn-secondary mt-3">View Product</a>--%>

<!-- Bootstrap JS and Popper.js are required for Bootstrap components to work -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
