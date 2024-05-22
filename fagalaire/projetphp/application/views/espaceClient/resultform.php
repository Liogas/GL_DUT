<a href="<?php echo base_url(); ?>index.php/utilisateur/espaceClient" id="retour"><i class="fas fa-backward fa-2x"></i></a>
<div class="resultForm">
    <section>
        <div class="haut">
            <h1><?php echo "$stitle : $acount réponses"?></h1>
            <p class="description"><?php echo $desc ?></p>
        </div>
        <div class="corps">
            <?php
                if($acount>0){
                    foreach ($questions as $question) {
                        $qid = $question['qid'];
                        $qtitle = $question['qtitle'];
                        $qtype = $question['qtype'];
                        $qchoices = explode(";",$question['qchoice']);
                        $choicecount = count($qchoices);
                        $choicestats = $qchoices;
                        $totalrep = 0;
                        foreach ($choicestats as $key => $s) {
                            $choicestats[$s] = 0;
                            unset($choicestats[$key]);
                        }
                        echo "<p class=\"questions\">$qtitle</p>";
                        foreach ($reponses as $reponse) {
                            if($qid == $reponse['qid']){
                                $rep = $reponse['text'];
                                if($qtype == 'checkbox' || $qtype == 'radio' || $qtype == 'list'){
                                    $repchoices = explode(';',$rep);
                                    foreach ($qchoices as $qchoice) {
                                        foreach ($repchoices as $repchoice) {
                                            if($repchoice == $qchoice){
                                                $choicestats[$qchoice] += 1;
                                                $totalrep += 1;
                                            }
                                        }
                                    }
                                } else {
                                    echo "<p class=\"result\">$rep</p>";
                                }
                            }
                        }
                        if($qtype == 'checkbox' || $qtype == 'radio' || $qtype == 'list'){
                            foreach($choicestats as $key => $stat){
                                $pourcentage = round(($stat*100) / ($totalrep),2);
                                echo "<p class=\"result\">$key : $pourcentage %</p>";
                            }
                        }
                    }                    
                }
            ?>
        </div>
    </section>
</div>