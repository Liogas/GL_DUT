<div class="blockForm">

    <div class="block1">
        <h4><?php echo $stitle ?> :</h4>
        <?php 
            if($activate){ 
                echo "<p class=\"etatA\">Activé</p>";
            } else{ 
                echo "<p class=\"etatD\">Désactivé</p>";
            }
        ?>
        <p class="description"><?php echo $description ?></p>
</div>

<ul>
    <li><i class="fas fa-chart-pie fa-2x" name="<?php echo $skey ?>" id="result"></i></li>
    <?php          
        if($activate){
            echo "<li><i class=\"fas fa-power-off fa-2x\" name=\"$skey\" id=\"desactiver\"></i></li>";
        } else {
            echo "<li><i class=\"fas fa-check fa-2x\" name=\"$skey\" id=\"activer\"></i></li>";
        }
    ?>
    <li><i class="fas fa-trash fa-2x" name="<?php echo $skey ?>" id="supprimer"></i></li>
    <li>
        <p class="pClé">
            <div class="clé">
                <input type="text" hidden>
                <button onclick="copyClipboard('<?php echo $skey ?>')" class="js-copy" id="boutonClé">Lien</button>
            </div>
        </p>
    </li>
</ul>

</div>
<p class="dateForm">Créé le <?php echo date('d-m-Y H:i:s',strtotime($creation_date)) ?></p>