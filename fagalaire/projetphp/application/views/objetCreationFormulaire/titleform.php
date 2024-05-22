<a href="<?php echo base_url(); ?>index.php/utilisateur/log" id="retour"><i class="fas fa-backward fa-2x"></i></a>

<?php $this->load->helper('form'); ?>
    <?php echo validation_errors(); ?>
    <?php echo form_open('formulaire/send',array()); ?>
    	<fieldset>
    	<legend>Créer un formulaire</legend>
    	<div class="question" id="titre">
    		Titre : <input type="text" name="stitle" maxlength="256" placeholder="Donnez un titre à votre formulaire.." size="110" required>
    	</div>
    <script>
    $(document).ready(function() {
    	var x = 1;

        $('body').on('click', '.add_form_question', function() {
        	x++;
            var $elems = $('.question_titre').parent();
            $elems
                .first()
                .clone()
                .insertAfter($elems.last());
            $('div[id^="question_"]').each(
                function(i){
                    $(this).attr('id','question_' + i);
                });
            $('input[name$="[qtitle]"]').each(
                function(i){
                    $(this).attr('name','question['+i+'][qtitle]');
                });
            $('select[name$="[qtype]"]').each(
                function(i){
                    $(this).attr('name','question['+i+'][qtype]');
                });
            $('input[name$="[qhelp]"]').each(
                function(i){
                    $(this).attr('name','question['+i+'][qhelp]');
                });
            $('div[class^="question_choice"]').each(
                function(i){
                    $(this).attr('id','qchoice_' + i);
                });
            $('span[id^="actions_q_"]').each(
                function(i){
                    $(this).attr('id','actions_q_' + i);
                });            
        });

        $('body').on('click', '.delete_form_question', function() {
        	if(x>1){
        		$(this).closest('.question_titre').parent().remove();
        		x--;
        	} else {
        		alert('Suppression impossible, vous devez avoir au moins une question dans votre formulaire.');
        	}
        });

        $('body').on('change', 'select', function() {
        	var qtype = $(this).val();
        	var qid = ($(this).attr('name')).match(/[0-9]+/);
        	var qchoice = $('#qchoice_'+qid);
        	qchoice.empty();

        	if(qtype == 'text'){
        		$('#actions_q_'+qid).hide();
    	    	qchoice.append('<div class="textChoice"><input type="text" placeholder="Vous avez choisit un champ de texte pour cette question." readonly></div>');
    	   	} else if(qtype == 'textarea'){
    	   		$('#actions_q_'+qid).hide();
    	    	qchoice.append('<div class="textAreaChoice"><textarea placeholder="Vous avez choisit une zone de texte pour cette question" readonly></textarea></div>');
    	   	} else if(qtype == 'date'){
    	   		$('#actions_q_'+qid).hide();
    	    	qchoice.append('<div class="dateChoice"><input type="date" readonly></div>');
    	   	} else if(qtype == 'radio'){
    	   		$('#actions_q_'+qid).show();
    	    	qchoice.append('<div class="radioChoice"><input type="text" placeholder="Valeur du bouton radio" name="question['+qid+'][qchoice][]" required></div><div class="radio"><input type="text" placeholder="Valeur du bouton radio" name="question['+qid+'][qchoice][]" required></div>');
    	   	} else if(qtype == 'checkbox'){
    	   		$('#actions_q_'+qid).show();
    	    	qchoice.append('<div class="checkboxChoice"><input type="text" placeholder="Valeur de la case à cocher" name="question['+qid+'][qchoice][]" required></div><div class="checkbox"><input type="text" placeholder="Valeur de la case à cocher" name="question['+qid+'][qchoice][]" required></div>');
    	   	} else if(qtype == 'list'){
    	   		$('#actions_q_'+qid).show();
    	    	qchoice.append('<div class="selectChoice"><input type="text" placeholder="Valeur d\'un élément de la liste" name="question['+qid+'][qchoice][]" required></div><div class="select"><input type="text" placeholder="Valeur d\'un élément de la liste" name="question['+qid+'][qchoice][]" required></div>');
    	   	} else {
    	   		$('#actions_q_'+qid).hide();
    	   	}
        });

        $('body').on('click', '.add_question_choice', function() {
        	var qid = (($(this).parent().attr('id')).split('_'))[2];
        	var qtype = $('select[name^="question['+qid+'][qtype]"]').find(":selected").val();
        	var qchoice = $('div[id^="qchoice_'+qid+'"]');
        	
        	if(qchoice.children().length>30){
        		alert('Soyez raisonnable, si vous avez besoin d\'autant de propositions, créez une autre question');
        	} else {
    	    	if(qtype == 'radio'){
    		    	qchoice.append('<div class="radioChoice"><input type="text" placeholder="Valeur du bouton radio" name="question['+qid+'][qchoice][]" required></div>');
    		   	} else if(qtype == 'checkbox'){
    		    	qchoice.append('<div class="checkboxChoice"><input type="text" placeholder="Valeur de la case à cocher" name="question['+qid+'][qchoice][]" required></div>');
    		   	} else if(qtype == 'list'){
    		    	qchoice.append('<div class="selectChoice"><input type="text" placeholder="Valeur d\'un élément de la liste" name="question['+qid+'][qchoice][]" required></div>');
    		   	}
    		}
        });

        $('body').on('click', '.delete_question_choice', function() {
        	var qid = (($(this).parent().attr('id')).split('_'))[2];
        	var qchoice = $('div[id^="qchoice_'+qid+'"]').children();
        	if(qchoice.length>2){
        		qchoice.last().remove();
        	} else {
        		alert('Suppression impossible, vous devez avoir au moins deux propositions.');
        	}
        });
    });
    </script>
