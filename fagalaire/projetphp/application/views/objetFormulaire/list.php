<?php $qname = $qid."_list";?>
<div class="select">
    <select name="<?php echo $qname?>" size="1" required>
        <?php
        	echo "<option value=\"\">Choissisez-en un(e)</option>";
        	foreach($choice as $c){
        		echo "<option value=\"$c\">",ucfirst($c),"</option>";
        	}
        ?>
    </select>
</div>  