<?php $qname = $qid."_radio";?>
<div class="radio">
	<?php
        foreach($choice as $c){
        	echo "<div class=\"\"><input type=\"radio\" name=\"$qname\" value=\"$c\">";
        	echo "<label>",ucfirst($c),"</label></div>";
        }
	?>
</div>