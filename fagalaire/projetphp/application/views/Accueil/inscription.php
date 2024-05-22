<div class="formConnect">
    <section>
        <?php echo form_open('utilisateur/inscription',array()); ?>
        <fieldset>
            <legend>S'inscrire</legend>

            <label>Login</label><br>
            <input value="<?=set_value('login')?>" type="textfield" name="login" placeholder="Saisissez un nom d'utilisateur.." required><br>

            <label>Mot de passe</label><br>
            <input value="<?=set_value('password')?>" type="password" name="password" placeholder="Saisissez un mot de passe.." required><br><br>

            <div class="submit">            
                <input type="submit" name="valider" value="Inscription" id="valider">
                <input type="reset" value="reset" id="reset">
            </div>
        </fieldset>
    </form>
    </section>
</div>