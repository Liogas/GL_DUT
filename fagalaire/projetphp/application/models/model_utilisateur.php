<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_utilisateur extends CI_Model {
	public function __construct(){
		return $this->load->database();
	}

	public function create($data){
		return $this->db->insert('utilisateur', $data);
	}

	public function delete($uid){
		return $this->db->delete('utilisateur', array('uid' => $uid));
	}

	public function check_login($login){
		$this->db->select('login')->from('utilisateur')->where('login',$login);
		$query = $this->db->get();
		if($query->num_rows()==0){
			return FALSE;
		}
		return TRUE;
	}

	public function getID($login){
		$this->db->select('uid')->from('utilisateur')->where('login',$login);
		return $this->db->get()->row()->uid;
	}

	public function getPasswordHash($login){
		if(!$this->check_login($login)){
			return FALSE;
		}
		$this->db->select('password')->from('utilisateur')->where('login',$login);
		$query = $this->db->get()->row();
		return $query->password;
	}
}