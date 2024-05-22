<?php $qname = $qid."_checkbox";?>
<div class="checkbox">
	<?php
        foreach($choice as $c){
        	echo "<div class=\"\"><input type=\"checkbox\" name=\"$qname","[]","\" value=\"$c\">";
        	echo "<label>",ucfirst($c),"</label></div>";
        }
	?>
</div>