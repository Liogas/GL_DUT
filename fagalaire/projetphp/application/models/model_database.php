<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_database extends CI_Model {
	public function __construct(){
		$this->load->database();
	}
	public function create(){
		include('sql_fields.php');
		
		foreach($table as $create_table){
			$this->db->query($create_table);
		}
		foreach($insert as $insertion){
			$this->db->query($insertion);
		}
	}
}