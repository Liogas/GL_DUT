<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Question extends CI_Controller {
    public function get_questions($skey){
    	$this->load->model('Model_question');
    	$questions = $this->Model_question->get_all($skey);
    	
    }
}
?>