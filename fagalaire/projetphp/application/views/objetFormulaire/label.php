<div class="question">
	<details <?php if(!is_null($help)) echo "open" ?> >
		<summary><label> <?php echo $question ?></label></summary>
		<p id="help"><?php if(is_null($help)){echo "Il n'y a pas d'aide pour cette question.";}else{echo $help;}; ?></p>
	</details>
</div>