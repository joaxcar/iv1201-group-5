import { useState } from "react";
import {
	Container,
	TextField,
	Grid,
	Typography,
	Button,
} from "@material-ui/core";
import { useForm, Controller } from "react-hook-form";
import { DatePicker } from "@material-ui/pickers";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

const validationSchema = yup.object().shape({
	firstName: yup.string().required(),
	lastName: yup.string().required(),
	email: yup.string().email().required(),
	dateOfBirth: yup.date().required(),
	username: yup.string().required(),
	password: yup.string().required().min(8),
});

function Registration() {
	const [date, setDate] = useState(new Date());

	const {
		register,
		handleSubmit,
		errors: validationErrors,
		control,
	} = useForm({
		resolver: yupResolver(validationSchema),
	});

	function onSubmit(validatedData, event) {
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
					month:
						validatedData.dateOfBirth.getMonth() + MONTH_ADJUSTER,
					day: validatedData.dateOfBirth.getDate(),
				},
			},
			username: validatedData.username,
			password: validatedData.password,
		};

		// TODO: POST DATA
		console.log(formattedRegistrationData);
	}

	return (
		<Container maxWidth="sm">
			<Typography variant="h4">Register</Typography>
			<form onSubmit={handleSubmit(onSubmit)}>
				<Grid container spacing={2}>
					<Grid item xs={12} sm={6}>
						<TextField
							label="First name"
							name="firstName"
							inputRef={register}
							fullWidth
							error={validationErrors.firstName ? true : false}
							helperText={
								validationErrors.firstName
									? "First name is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12} sm={6}>
						<TextField
							label="Last name"
							name="lastName"
							inputRef={register}
							fullWidth
							error={validationErrors.lastName ? true : false}
							helperText={
								validationErrors.lastName
									? "Last name is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12}>
						<TextField
							label="E-mail address"
							name="email"
							inputRef={register}
							fullWidth
							error={validationErrors.email ? true : false}
							helperText={
								validationErrors.email
									? "A valid e-mail address is required"
									: null
							}
						/>
					</Grid>
					<Grid item>
						<Controller
							name="dateOfBirth"
							control={control}
							defaultValue={date}
							render={() => (
								<DatePicker
									value={date}
									onChange={setDate}
									variant="inline"
									autoOk
									label="Date of birth"
									openTo="year"
									format="yyyy-MM-DD"
									error={
										validationErrors.dateOfBirth
											? true
											: false
									}
									helperText={
										validationErrors.dateOfBirth
											? "banan"
											: null
									}
								/>
							)}
						></Controller>
					</Grid>
					<Grid item xs={12}>
						<TextField
							label="Username"
							name="username"
							inputRef={register}
							fullWidth
							error={validationErrors.username ? true : false}
							helperText={
								validationErrors.username
									? "Username is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12}>
						<TextField
							label="Password"
							name="password"
							inputRef={register}
							type="password"
							fullWidth
							error={validationErrors.password ? true : false}
							helperText={
								validationErrors.password
									? "Password is required and should be at least 8 characters"
									: null
							}
						/>
					</Grid>
					<Grid item>
						<Button variant="contained" type="submit">
							Register
						</Button>
					</Grid>
				</Grid>
			</form>
		</Container>
	);
}

export default Registration;
