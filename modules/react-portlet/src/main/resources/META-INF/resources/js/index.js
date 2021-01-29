import React from 'react';
import ReactDOM from 'react-dom';

const HelloWorld = ({name, lastName}) => <div>
	<h1>Hello {name} {lastName}</h1>
</div>;
const Site = () => <div><span>Esto es una prueba de react en liferay</span></div>;

export default function(elementId, dataGlobals) {
	const {name, lastName} = dataGlobals;
//const name = "Luis"
//const lastName = "Miguel"
	ReactDOM.render(
		<div>
			<HelloWorld  name={name} lastName={lastName}/>
			<Site/>
		</div>
	, document.getElementById(elementId));
}