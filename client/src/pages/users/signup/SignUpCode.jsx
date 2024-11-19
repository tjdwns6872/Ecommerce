import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';

function SignUpCode() {

  return (
    <div className='signup-div signup-code'>
    <div className="form-group">
    {InputFactory.basic('user-phone', '휴대폰 번호', "", 'input-field')}
    </div>
    <div className="form-group">
    {InputFactory.basic('user-code', '인증번호를 입력하세요', "", 'input-field')}
    {ButtonFactory.basic('code-btn','인증번호 전송', () => '', '')}
    </div>
    </div>
  )
}

export default SignUpCode;