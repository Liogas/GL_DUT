<a href="<?php echo base_url();?>index.php/site/" id="retour"><i class="fas fa-backward fa-2x"></i></a>
<script>
    function copyClipboard(text) {
        var dummy = document.createElement("textarea");
        document.body.appendChild(dummy);
        dummy.value = text;
        dummy.select();
        document.execCommand("copy");
        document.body.removeChild(dummy);
        alert("La clé de ce formulaire a bien été copié : " + dummy.value+'.\r\rPensez à activer le formulaire avant de partager sa clé !');
    }

    $(document).ready(function() {
        $("i[class^=fas]").click(function() {
            action = $(this).attr('id');
            key = $(this).attr('name');
            base_url = "<?php echo base_url()?>";
            if(action == "supprimer"){
                var conf = confirm('Etes-vous sûr de vouloir supprimer ce formulaire ?');
                if(conf){
                    window.location.replace(base_url+'index.php/formulaire/delete/'+key);
                }
            }
            if(action == "activer"){
                var conf = confirm('Etes-vous sûr de vouloir activer ce formulaire ?');
                if(conf){
                    window.location.replace(base_url+'index.php/formulaire/activate/'+key);
                }
            }
            if(action == "desactiver"){
                var conf = confirm('Etes-vous sûr de vouloir désactiver ce formulaire ?');
                if(conf){
                    window.location.replace(base_url+'index.php/formulaire/desactivate/'+key);
                }
            }
            if(action == "result"){
                window.location.replace(base_url+'index.php/formulaire/viewResult/'+key);
            }
        });
    });
</script>


<div class="espaceClient"> 

<section>
    <div class="haut">
        <h2>Bienvenue sur votre page client <?php echo $_SESSION['login'] ?> !</h2>
    </div>


