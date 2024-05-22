<?php
    defined('BASEPATH') OR exit('No direct script access allowed');
    $this->load->helper('url');
    if (!isset($_SESSION)) { session_start(); };
    ?>
    <!DOCTYPE html> 
    <html lang = "fr">
     
       <head> 
          <meta charset = "utf-8"> 
          <title>formulaire</title> 
          <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
          <link rel="stylesheet" type="text/css" href="<?php echo base_url();?>assets/css/style.css">
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
       </head>

       <body>
