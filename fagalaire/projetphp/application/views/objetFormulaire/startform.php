<?php $this->load->helper('form'); ?>
<?php echo validation_errors(); ?>
<?php echo form_open('formulaire/answer',array()); ?>
	<fieldset>
	<legend> <?php echo $title; ?></legend>
	<div class="formDescription">
		<p>Description : <?php echo $description ?></p>
	</div>