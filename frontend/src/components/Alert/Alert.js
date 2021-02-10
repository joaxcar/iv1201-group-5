import { useState } from "react";
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from "@material-ui/lab/Alert";

function Alert({ type, children }) {
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
				{children}
			</MuiAlert>
		</Snackbar>
	);
}

export default Alert;
