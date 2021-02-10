import Registration from "../../components/Registration/Registration";
import { ACCOUNTS } from "../../properties/endpoints";
import { postToAPI } from "../../util/network";

function formatDetails(registrationDetails) {
	const MONTH_ADJUSTER = 1;
	return {
		name: {
			first: registrationDetails.firstName,
			last: registrationDetails.lastName,
		},
		email: registrationDetails.email,
		dateOfBirth: {
			year: registrationDetails.dateOfBirth.getFullYear(),
			month: registrationDetails.dateOfBirth.getMonth() + MONTH_ADJUSTER,
			day: registrationDetails.dateOfBirth.getDate(),
		},
		username: registrationDetails.username,
		password: registrationDetails.password,
	};
}

function handleFormSubmit(registrationDetails, event) {
	event.preventDefault();
	event.target.reset();

	const formattedRegistrationDetails = formatDetails(registrationDetails);

	postToAPI(ACCOUNTS, formattedRegistrationDetails)
		.then(console.log)
		.catch(console.log);
}

function RegistrationContainer() {
	return <Registration onSubmit={handleFormSubmit} />;
}

export default RegistrationContainer;
