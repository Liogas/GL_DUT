<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_question extends CI_Model {
	public function __construct(){
		return $this->load->database();
	}

	public function add($data){
		return $this->db->insert('question', $data);
	}

	public function delete($qid){
		return $this->db->delete('question', array('qid' => $qid));
	}

	public function get_all($skey){
		$this->db->from('question')->where('skey',$skey);
		$query = $this->db->get();
		return $query->result_array();
	}

	public function get_surveyid($qid){
		$this->db->select('skey')->from('question')->where('qid',$qid);
		$query = $this->db->get();
		$data = $query->row();
		return $data->skey;
	}

	public function count($skey){
		$this->db->from('question')->where('skey',$skey);
		return $this->db->count_all_results();
	}
}