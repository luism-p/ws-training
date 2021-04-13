import React from 'react';
import ReactDOM from 'react-dom';
import ClayForm, {ClayInput} from '@clayui/form';

const HelloWorld = ({name, lastName}) => <div>
	<h1>Hello {name} {lastName}</h1>
</div>;
const Site = () => <div><span>Esto es una prueba de react en liferay</span></div>;
const Formula = ({message}) => {
	const [active, setActive] = useState(false);

	return (
		<ClayForm>
			<ClayForm.Group className="form-group-sm" >
				<label htmlFor="in">Name</label>
				<ClayInput id="in" placeholder="Name" type="text" />
				<ClayForm.FeedbackGroup>
					<ClayForm.FeedbackItem>{message}</ClayForm.FeedbackItem>
				</ClayForm.FeedbackGroup>
			</ClayForm.Group>

		</ClayForm>
	);};

export default function(elementId, user) {
const {name, lastName, message} = user

//const name = "Luis"
//const lastName = "Miguel"
	ReactDOM.render(



		<div>
			<HelloWorld  name={name} lastName={lastName}/>
			<Site/>
			<Formula message={message}/>
		</div>
	, document.getElementById(elementId));
}

