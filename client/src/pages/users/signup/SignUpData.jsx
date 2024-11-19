import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';

function SignUpData() {

  return (
    <div className='signup-div signup-data'>
      <div className="signup-form">
        <form>
          <div className="form-group">
            {InputFactory.basic('user-email', '이메일을 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.password('user-pw', '비밀번호를 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.password('user-pw-check', '비밀번호 확인', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.basic('user-name', '이름을 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.basic('user-birth-date', '생년월일 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.basic('user-referral-code', '추천인 코드를 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {ButtonFactory.basic('signup-btn','회원가입', () => '', 'w-100')}
          </div>
        </form>
      </div>
    </div>
  )
}
export default SignUpData;