import { useState } from "react";
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from "@material-ui/lab/Alert";

/**
 * A component that shows up at the bottom of the screen
 * for six seconds and display a message to the user.
 * @param {alertType} type - The type of alert
 * @param {string} message - The message to show
 */
function Alert({ type, message }) {
	const [open, setOpen] = useState(true);

	const handleClose = (event, reason) => {
		if (reason === "clickaway") {
			return;
		}

		setOpen(false);
	};

	return (
		<Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
			<MuiAlert
				elevation={6}
				variant="filled"
				onClose={handleClose}
				severity={type}
			>
				{message}
			</MuiAlert>
		</Snackbar>
	);
}

export default Alert;
