import { useState, useEffect } from "react";
import Profile from "../../components/Profile/Profile";
import alertTypes from "../../properties/alerttypes";
import Alert from "../../components/Alert/Alert";

/**
 * Container for Profile.
 * * @param {number} accountId - The account id for the user
 */
function ProfileContainer({ accountId }) {
	const [alert, setAlert] = useState({ open: false, type: "", message: "" });
	const [user, setUser] = useState(null);

	function showAlert(type, message) {
		setAlert({ open: true, type, message });
		setTimeout(() => setAlert({ ...alert, open: false }), 7000);
	}

	function loadUser(id) {
		fetch("/api/v1/account/" + id, {
			headers: { "Content-Type": "application/json" },
		})
			.then((resp) => resp.json())
			.then((resp) => {
				setUser({
					name: `${resp.person.name.first} ${resp.person.name.last}`,
					birthDate: resp.person.birthDate,
					email: resp.person.email,
				});
			})
			.catch((err) =>
				showAlert(alertTypes.ERROR, "Something went wrong")
			);
	}

	useEffect(() => {
		loadUser(accountId);
	}, []);

	return (
		<>
			{alert.open ? (
				<Alert type={alert.type} message={alert.message} />
			) : null}
			<Profile accountId={accountId} user={user} />
		</>
	);
}

export default ProfileContainer;
