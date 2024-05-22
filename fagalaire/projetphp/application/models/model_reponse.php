<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_reponse extends CI_Model {
	public function __construct(){
		return $this->load->database();
	}

	public function submit($data){
		return $this->db->insert('reponse', $data);
	}

	public function delete($aid){
		return $this->db->delete('reponse', array('aid' => $aid));
	}

	public function get_all($skey){
		$this->db->from('reponse')->where('skey',$skey);
		$query = $this->db->get();
		return $query->result_array();		
	}

	public function count($skey){
		$this->db->from('reponse')->where('skey',$skey);
		return $this->db->count_all_results();
	}	
}