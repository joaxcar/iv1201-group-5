import { useState, useContext } from "react";
import Registration from "../../components/Registration/Registration";
import endpoints from "../../properties/endpoints";
import { postToAPI } from "../../util/network";
import alertTypes from "../../properties/alerttypes";
import { AlertContext } from "../../App";

function formatDetails({
	firstName,
	lastName,
	email,
	birthYear,
	birthMonth,
	birthDay,
	username,
	password,
}) {
	const dateOfBirth = `${birthYear}-${String(birthMonth).padStart(
		2,
		"0"
	)}-${String(birthDay).padStart(2, "0")}`;

	return {
		name: {
			first: firstName,
			last: lastName,
		},
		email: email,
		dateOfBirth,
		username: username,
		password: password,
	};
}

/**
 * Container for Registration.
 */
function RegistrationContainer() {
	const showAlert = useContext(AlertContext);

	function handleFormSubmit(registrationDetails, event) {
		event.preventDefault();
		const formattedRegistrationDetails = formatDetails(registrationDetails);

		postToAPI(endpoints.ACCOUNTS, formattedRegistrationDetails)
			.then((response) => {
				event.target.reset();
				showAlert(alertTypes.SUCCESS, "Registration was successful");
			})
			.catch((error) => {
				if (error.message === "Conflict") {
					document.getElementsByName("username")[0].value = "";
					showAlert(alertTypes.ERROR, "User already exists");
				} else {
					showAlert(alertTypes.ERROR, "Something went wrong");
				}
			});
	}

	return <Registration onSubmit={handleFormSubmit} />;
}

export default RegistrationContainer;
