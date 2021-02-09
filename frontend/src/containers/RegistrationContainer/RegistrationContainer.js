import Registration from "../../components/Registration/Registration";
import { ACCOUNT } from "../../properties/endpoints";

function postRegistrationDetails(registrationDetails) {
	return fetch(ACCOUNT, {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(registrationDetails),
	}).then((response) => response.json());
}

function handleFormSubmit(validatedData, event) {
	const MONTH_ADJUSTER = 1;
	event.preventDefault();
	event.target.reset();

	const formattedRegistrationData = {
		name: {
			first: validatedData.firstName,
			last: validatedData.lastName,
		},
		email: validatedData.email,
		dateOfBirth: {
			date: {
				year: validatedData.dateOfBirth.getFullYear(),
				month: validatedData.dateOfBirth.getMonth() + MONTH_ADJUSTER,
				day: validatedData.dateOfBirth.getDate(),
			},
		},
		username: validatedData.username,
		password: validatedData.password,
	};

	postRegistrationDetails(formattedRegistrationData)
		.then(console.log)
		.catch(console.log);
}

function RegistrationContainer() {
	return <Registration onSubmit={handleFormSubmit} />;
}

export default RegistrationContainer;
