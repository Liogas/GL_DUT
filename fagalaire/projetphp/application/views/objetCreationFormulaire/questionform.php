<div id="question_0">
	<div class="question_titre">
		Posez-votre question : 
		<input type="text" name="question[0][qtitle]" maxlength="512" size="80" placeholder="Ecrivez votre question.." class="inputQuestion" required> 
		<input type="button" class="add_form_question" value="Ajouter"> 
		<input type="button" class="delete_form_question" value="Supprimer">
	</div>
	<div class="question_help">
		Indication(s) : 
		<input type="text" name="question[0][qhelp]" maxlength="128" size="80" placeholder="Précisez une indication sur la manière de répondre si nécessaire" class="inputHelp"> 
	</div>
	<select name="question[0][qtype]" class="select_qtype">
		<option value="" selected>Choissisez un type de question</option>
		<option value="checkbox">Liste à cocher</option>
		<option value="list">Liste</option>
		<option value="text">Champ de texte</option>
		<option value="textarea">Zone de texte</option>
		<option value="date">Date</option>
		<option value="radio">Bouttons radio</option>
	</select>
	<span class="actions" id="actions_q_0" hidden>
		<input type="button" class="add_question_choice" name="add" value="+"> 
		<input type="button" class="delete_question_choice" name="delete" value="-" id="d">
	</span>
	
	<div class="question_choice" id="qchoice_0"></div>
</div>