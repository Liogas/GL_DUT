<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Formulaire extends CI_Controller {
    public function create(){
        $this->checkSession();

        $this->load->view('head');
        $this->load->view('header');
        $this->load->view('objetCreationFormulaire/titleForm');
        $this->load->view('objetCreationFormulaire/descriptionForm');
        $this->load->view('objetCreationFormulaire/questionForm');
        $this->load->view('objetFormulaire/endForm');
        $this->load->view('footer');
    }

    public function send(){
        $this->checkSession();
        $this->load->helper('form');
        $this->load->library('form_validation');
        $this->load->model('Model_question');
        $this->load->model('Model_formulaire');
        $this->load->model('Model_utilisateur');
        date_default_timezone_set('Europe/Paris');

        $this->form_validation->set_rules('stitle','Title','callback_checkQtype');

        if($this->form_validation->run() === FALSE){
            $this->load->view('head');
            $this->load->view('header');
            $this->load->view('objetCreationFormulaire/titleForm');
            $this->load->view('objetCreationFormulaire/descriptionForm');
            $this->load->view('objetCreationFormulaire/questionForm');
            $this->load->view('objetFormulaire/endForm');
            $this->load->view('footer');
        } else {
            $creator = $this->Model_utilisateur->getID($_SESSION['login']);
            $skey = $this->randomKey(64);
            $formulaire = array('skey' => $skey, 'creator_id' => $creator, 'stitle' => $this->input->post('stitle'), 'description' => $this->input->post('description'), 'activate' => FALSE, 'creation_date' => date('Y-m-d H:i:s'), 'activation_date' => NULL, 'lastmodified' => NULL);

            $this->Model_formulaire->create($formulaire);
            foreach ($this->input->post('question') as $question) {
                if(empty($question['qchoice'])){
                    $qchoice = NULL;
                } else {
                    $qchoice = implode(';',$question['qchoice']);
                }
                if(empty($question['qhelp'])){
                    $qhelp = NULL;
                } else {
                    $qhelp = $question['qhelp'];
                }
                $data = array ('skey' => $skey, 'qtitle' => $question['qtitle'], 'qtype' => $question['qtype'], 'qchoice' => $qchoice, 'help' => $qhelp);
                $this->Model_question->add($data);
            }
            redirect('utilisateur/espaceclient');
        }
    }

    public function view(){
    	$this->load->model('Model_question');
    	$this->load->model('Model_formulaire');

        $skey = $this->input->post('nomForm');
        $url= $_SERVER['HTTP_REFERER'];

        if(!$this->Model_formulaire->exist($skey) || !$this->Model_formulaire->isActivate($skey)){
            echo "<script>alert('Ce formulaire n\'existe pas ou n\'est pas encore activé !'); window.location.href='$url';</script>";
        } else {
            $formheader = array('title' => $this->Model_formulaire->get_title($skey), 'description' => $this->Model_formulaire->get_desc($skey));
            $questions = $this->Model_question->get_all($skey);

            $this->load->view('head');
            $this->load->view('objetFormulaire/startForm', $formheader);
            foreach($questions as $question){
                $choice = explode(';', $question['qchoice']);
                $qtype = $question['qtype'];
                $type = "objetFormulaire/$qtype";
                $data = array('qid' => $question['qid'], 'question' => $question['qtitle'], 'choice' => $choice, 'help' => $question['help']);

                $this->load->view('objetFormulaire/startReponse');
                $this->load->view('objetFormulaire/label', $data);
                $this->load->view($type,$data);
                $this->load->view('objetFormulaire/endDiv');
            }
            $this->load->view('objetFormulaire/endForm');
            $this->load->view('footer');            
        }
    }

    public function viewResult($skey){
        $this->checkSession();
        $this->load->model('Model_formulaire');

        if(!$this->Model_formulaire->check_owner($skey, $this->session->login)){
            $this->load->view('errors/index.html');
        } else {
            $this->load->model('Model_question');
            $this->load->model('Model_reponse');
            $qtions = $this->Model_question->get_all($skey);
            $reps = $this->Model_reponse->get_all($skey);
            $rcount = ($this->Model_reponse->count($skey))/($this->Model_question->count($skey));
            $info = array('stitle' => $this->Model_formulaire->get_title($skey), 'desc' => $this->Model_formulaire->get_desc($skey), 'acount' => $rcount, 'questions' => $qtions, 'reponses' => $reps);
            $this->load->view('head');
            $this->load->view('header');
            $this->load->view('espaceClient/resultForm',$info);
            $this->load->view('footer');            
        }
    }

    public function answer(){
        $this->load->helper('form');
        $this->load->library('form_validation');
        $this->load->model('Model_question');
        $this->load->model('Model_reponse');
        date_default_timezone_set('Europe/Paris');

        $result = $this->input->post();
        foreach ($result as $key => $answer){
            $q = explode('_', $key);
            $qid = $q[0];
            $qtype = $q[1];
            if(!isset($skey)){
                $skey = $this->Model_question->get_surveyid($qid);
            }
            if($qtype === 'checkbox'){
                $content = implode(";", $answer);
            } else {
                $content = $answer;
            }
            $data = array('qid' => intval($qid), 'skey' => $skey, 'text' => $content, 'submission_date' => date('Y-m-d H:i:s'));
            $this->Model_reponse->submit($data);
        }
        redirect('site');
    }

    public function checkSession(){
        if(!$this->session->logged){
            redirect('site');
        } else {
            return TRUE;
        }
    }

    public function checkQtype(){
        foreach ($this->input->post('question') as $question) {
            if(empty($question['qtype'])){
                $this->form_validation->set_message('checkQtype','Merci de choisir un type de question pour : '.$question['qtitle']);
                return FALSE;
            }
        }
        return TRUE;
    }

    public function activate($skey){
        $this->checkSession();
        $this->load->model('Model_formulaire');
        if(!$this->Model_formulaire->check_owner($skey, $this->session->login)){
            $this->load->view('errors/index.html');
        } else {
            $this->Model_formulaire->activate($skey);
            redirect('utilisateur/espaceclient');
        }
    }

    public function desactivate($skey){
        $this->checkSession();
        $this->load->model('Model_formulaire');
        if(!$this->Model_formulaire->check_owner($skey, $this->session->login)){
            $this->load->view('errors/index.html');
        } else {
            $this->Model_formulaire->desactivate($skey);
            redirect('utilisateur/espaceclient');
        }
    }

    public function delete($skey){
        $this->checkSession();
        $this->load->model('Model_formulaire');
        if(!$this->Model_formulaire->check_owner($skey, $this->session->login)){
            $this->load->view('errors/index.html');
        } else {
            $this->Model_formulaire->delete($skey);
            redirect('utilisateur/espaceclient');
        }
    }

    function randomKey($length_of_string){ 
        $str_result = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
        $key = '';
        for($i=0;$i<$length_of_string;$i++){
            $rand = rand(0,strlen($str_result)-1);
            $key .= $str_result[$rand];
        }
        return $key; 
    } 
}
?>