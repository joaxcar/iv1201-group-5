import {
	Container,
	TextField,
	Grid,
	Typography,
	Button,
	Select,
	InputLabel,
	MenuItem,
} from "@material-ui/core";
import { useForm, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import * as calendar from "../../util/calendar";
import classes from "./registration.module.css";

const validationSchema = yup
	.object()
	.shape({
		firstName: yup.string().required(),
		lastName: yup.string().required(),
		email: yup.string().email().required(),
		birthYear: yup.number().positive().required(),
		birthMonth: yup.number().required(),
		birthDay: yup.number().positive().max(31).required(),
		username: yup.string().required(),
		password: yup.string().required().min(8),
	})
	.test(
		"date",
		"Date must be existing and past",
		function ({ birthYear, birthMonth, birthDay }) {
			return (
				calendar.isExistingDate(birthYear, birthMonth, birthDay) &&
				calendar.isPastDate(birthYear, birthMonth, birthDay)
			);
		}
	);

/**
 * Form for registrating new users.
 * @param {function} onSubmit - The callback function to run on submit
 */
function Registration({ onSubmit }) {
	const {
		register,
		handleSubmit,
		control,
		errors: validationErrors,
	} = useForm({
		resolver: yupResolver(validationSchema),
	});

	return (
		<Container>
			<div className={classes.registration}>
				<Typography variant="h4">Register</Typography>
				<form onSubmit={handleSubmit(onSubmit)}>
					<Grid container spacing={2}>
						<Grid item xs={12} sm={6}>
							<TextField
								label="First name"
								name="firstName"
								inputRef={register}
								fullWidth
								error={
									validationErrors.firstName ? true : false
								}
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
						<Grid item xs={4}>
							<InputLabel id="selecBirthYear">
								Year of birth
							</InputLabel>
							<Controller
								as={
									<Select labelId="selectBirthYear" fullWidth>
										{calendar.getYears().map((year) => (
											<MenuItem value={year} key={year}>
												{year}
											</MenuItem>
										))}
									</Select>
								}
								name="birthYear"
								control={control}
								defaultValue={2004}
							/>
						</Grid>
						<Grid item xs={4}>
							<InputLabel id="selecBirthMonth">
								Month of birth
							</InputLabel>
							<Controller
								as={
									<Select
										labelId="selectBirthMonth"
										fullWidth
									>
										{calendar.getMonths().map((month) => (
											<MenuItem value={month} key={month}>
												{month}
											</MenuItem>
										))}
									</Select>
								}
								name="birthMonth"
								defaultValue={1}
								control={control}
							/>
						</Grid>
						<Grid item xs={4}>
							<InputLabel id="selecBirthDay">
								Day of birth
							</InputLabel>
							<Controller
								as={
									<TextField
										select
										fullWidth
										error={
											validationErrors.date ? true : false
										}
										helperText={
											validationErrors.date
												? "Date must exist and be past"
												: null
										}
									>
										{calendar.getDays().map((day) => (
											<MenuItem value={day} key={day}>
												{day}
											</MenuItem>
										))}
									</TextField>
								}
								control={control}
								name="birthDay"
								defaultValue={1}
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
			</div>
		</Container>
	);
}

export default Registration;
