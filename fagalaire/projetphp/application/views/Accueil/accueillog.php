<div class="log">

    <section class="titre">
        <h2>Bonjour <?php echo $_SESSION['login']; ?> !</h2>
    </section>
    
    <section>
        <nav>
            <li><a href="<?php echo base_url();?>index.php/utilisateur/espaceClient"><i class="fas fa-user fa-4x"></i></a></li>
            <li><a href="<?php echo base_url();?>index.php/formulaire/create"><i class="fas fa-plus fa-4x"></i></a></li>
            <li><a href="<?php echo base_url();?>index.php/utilisateur/deconnexion" id="deco"><i class="fas fa-power-off fa-4x"></i></a></li>
        </nav>
    </section>

</div>