<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Model_formulaire extends CI_Model {
	public function __construct(){
		$this->load->database();
	}

	public function create($data){
		$this->db->insert('formulaire', $data);
	}

	public function exist($skey){
		$this->db->select('skey')->from('formulaire')->where('skey',$skey);
		$query = $this->db->get()->row();
		if(is_null($query)){
			return FALSE;
		} else {
			return TRUE;
		}
	}

	public function get_title($skey){
		$this->db->select('stitle')->from('formulaire')->where('skey',$skey);
		$query = $this->db->get()->row();
		if(empty($query)){
			redirect('utilisateur/log');
		}
		return $query->stitle;
	}

	public function get_desc($skey){
		$this->db->select('description')->from('formulaire')->where('skey',$skey);
		$query = $this->db->get()->row();
		if(empty($query)){
			redirect('utilisateur/log');
		}
		return $query->description;
	}

	public function get_all_form($login){
		$this->load->model('Model_utilisateur');
		$id = $this->Model_utilisateur->getID($login);
		$this->db->from('formulaire')->where('creator_id', $id);
		$query = $this->db->get();
		return $query->result_array();
	}

	public function delete($skey){
		$this->db->delete('formulaire', array('skey' => $skey));
	}

	public function check_owner($skey,$login){
		$this->load->model('Model_utilisateur');
		$id_user = $this->Model_utilisateur->getID($login);
		$this->db->select('creator_id')->from('formulaire')->where('skey',$skey);
		$id_owner = $this->db->get()->row()->creator_id;
		if($id_user == $id_owner){
			return TRUE;
		} else {
			return FALSE;
		}
	}

	public function activate($skey){
		$this->db->query("UPDATE `formulaire` SET `activate` = '1' WHERE `formulaire`.`skey` = '$skey'");
	}

	public function desactivate($skey){
		$this->db->query("UPDATE `formulaire` SET `activate` = '0' WHERE `formulaire`.`skey` = '$skey'");
	}

	public function isActivate($skey){
		$this->db->select('activate')->from('formulaire')->where('skey',$skey);
		$query = $this->db->get()->row();
		if($query->activate){
			return TRUE;
		} else {
			return FALSE;
		}
	}
}