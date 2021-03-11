import { useState, useEffect, useContext } from "react";
import { getFromAPI } from "../../util/network";
import endpoints from "../../properties/endpoints";
import Profile from "../../components/Profile/Profile";
import alertTypes from "../../properties/alerttypes";
import { AlertContext } from "../../App";

/**
 * Container for Profile.
 * * @param {number} accountId - The account id for the user
 */
function ProfileContainer({ accountId }) {
	const [user, setUser] = useState(null);
	const showAlert = useContext(AlertContext);

	useEffect(() => {
		const url = `${endpoints.ACCOUNT}/${accountId}`;
		getFromAPI(url)
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
	}, []);

	return <Profile accountId={accountId} user={user} />;
}

export default ProfileContainer;
